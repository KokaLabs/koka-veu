package koka.veu;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
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
  };

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    gridview = (GridView) this.getActivity().findViewById(R.id.photogrid);
    gridview.setAdapter(new PhotoImageAdapter(this.getActivity()));
    gridview.setOnItemClickListener(onPhotoClick());
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

  protected abstract AdapterView.OnItemClickListener onPhotoClick();

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
  }

  public interface Callbacks {
    void onPhotoSelected(Integer photoId);
  }

  public static final class TwoPanePhotosFragment extends PhotosFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      gridview.setChoiceMode(GridView.CHOICE_MODE_SINGLE);
    }

    @Override
    protected AdapterView.OnItemClickListener onPhotoClick() {
      final Activity a = this.getActivity();
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
    protected AdapterView.OnItemClickListener onPhotoClick() {
      return new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
          Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
        }
      };
    }
  }
}