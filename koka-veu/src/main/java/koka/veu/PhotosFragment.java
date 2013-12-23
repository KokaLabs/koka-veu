package koka.veu;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

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
    public void onPhotosSelected(List<Integer> photoIds) {
    }
  };

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    gridview = (GridView) this.getActivity().findViewById(R.id.photogrid);
    gridview.setAdapter(new PhotoImageAdapter(this.getActivity()));
    gridview.setOnItemClickListener(onPhotoSelected());
    gridview.setMultiChoiceModeListener(
        new GalleryMultiChoiceListener() {
          @Override
          protected Callbacks callback() {
            return mCallbacks;
          }
        }
    );
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

    void onPhotosSelected(List<Integer> photoIds);
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
//          PorterDuffColorFilter pd = new PorterDuffColorFilter(Color.argb(200, 255, 255, 255), PorterDuff.Mode.SRC_OVER);
//          ((ImageView) v).setColorFilter(pd);
////          ColorMatrix cm = new ColorMatrix();
////          cm.setSaturation(0);
//
////          v.setAlpha(0);
//          v.setBackgroundColor(Color.BLACK);
          ((ImageView) v).setColorFilter(Color.rgb(123, 123, 123), android.graphics.PorterDuff.Mode.MULTIPLY);

          mCallbacks.onPhotoSelected(PhotoImageAdapter.getItemAt(position));
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