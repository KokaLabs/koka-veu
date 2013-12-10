package koka.veu;

import android.app.Fragment;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;

public class PhotosFragment extends Fragment {
  protected GridView gridview;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    gridview = (GridView) this.getActivity().findViewById(R.id.photogrid);
    gridview.setAdapter(new PhotoImageAdapter(this.getActivity()));
  }

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
  }

  public static final class SinglePanePhotosFragment extends PhotosFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      gridview.setChoiceMode(GridView.CHOICE_MODE_NONE);
    }
  }
}