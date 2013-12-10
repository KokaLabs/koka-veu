package koka.veu;

import android.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public abstract class PhotosFragment extends Fragment {
  protected GridView gridview;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    gridview = (GridView) this.getActivity().findViewById(R.id.photogrid);
    gridview.setAdapter(new PhotoImageAdapter(this.getActivity()));

    gridview.setOnItemClickListener(onPhotoClick());
  }

  protected abstract AdapterView.OnItemClickListener onPhotoClick();

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
  }

  public interface Callbacks {
    void onItemSelected(String id);
  }

  public static final class TwoPanePhotosFragment extends PhotosFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      gridview.setChoiceMode(GridView.CHOICE_MODE_SINGLE);
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