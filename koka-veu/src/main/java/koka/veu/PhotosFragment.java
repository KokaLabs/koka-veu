package koka.veu;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public abstract class PhotosFragment extends Fragment {
  protected GridView gridview;
  protected Callbacks mCallbacks;

  /**
   * A dummy implementation of the {@link Callbacks} interface that does
   * nothing. Used only when this fragment is not attached to an activity.
   */
  private static Callbacks sDummyCallbacks = new Callbacks() {
    @Override
    public void onPhotoSelected(Integer photoId) {
    }

    @Override
    public void onPhotosSelected(Integer[] photoIds) {
    }
  };

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    gridview = (GridView) this.getActivity().findViewById(R.id.photogrid);
    gridview.setAdapter(new PhotoImageAdapter(this.getActivity()));
    gridview.setOnItemClickListener(onPhotoSelected());
    gridview.setMultiChoiceModeListener(new GridView.MultiChoiceModeListener() {
      @Override
      public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        MenuInflater inflater = actionMode.getMenuInflater();
        inflater.inflate(R.menu.gallery_multiselect, menu);
        return true;
      }

      @Override
      public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return false;
      }

      @Override
      public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        StringBuilder sb = new StringBuilder();
//        Set<Integer> positions = mAdapter.getCurrentCheckedPosition();
//        for (Integer pos : positions) {
//          sb.append(" " + pos + ",");
//        }
        sb.append("rawr");
        Toast.makeText(getActivity(), sb.toString(), Toast.LENGTH_SHORT).show();
        actionMode.finish();
        return false;
      }

      public void onItemCheckedStateChanged(
          ActionMode mode, int position, long id, boolean checked) {
        int selectCount = gridview.getCheckedItemCount();
        switch (selectCount) {
          case 1:
            mode.setSubtitle("One item selected");
            break;
          default:
            mode.setSubtitle("" + selectCount + " items selected");
            break;
        }
      }

      @Override
      public void onDestroyActionMode(ActionMode actionMode) {
//        mAdapter.clearSelection();
      }
    });
  }

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (!(activity instanceof Callbacks)) {
      throw new IllegalStateException("Activity must implement fragment's callbacks.");
    }
    mCallbacks = (Callbacks) activity;
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mCallbacks = sDummyCallbacks;
  }

  protected abstract AdapterView.OnItemClickListener onPhotoSelected();

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
  }

  public interface Callbacks {
    void onPhotoSelected(Integer photoId);

    void onPhotosSelected(Integer[] photoIds);
  }

  public static final class TwoPanePhotosFragment extends PhotosFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      gridview.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE_MODAL);
    }

    @Override
    protected AdapterView.OnItemClickListener onPhotoSelected() {
      return new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
          mCallbacks.onPhotoSelected(PhotoImageAdapter.getItemAt(position));
          Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
        }
      };
    }
  }

  public static final class SinglePanePhotosFragment extends PhotosFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      gridview.setChoiceMode(GridView.CHOICE_MODE_NONE);
    }

    @Override
    protected AdapterView.OnItemClickListener onPhotoSelected() {
      return new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
          Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
        }
      };
    }
  }
}