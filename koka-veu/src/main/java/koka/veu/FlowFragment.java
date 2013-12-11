package koka.veu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlowFragment extends Fragment {
  private List<Integer> photos = new ArrayList<Integer>();

  /**
   * Mandatory empty constructor for the fragment manager to instantiate the
   * fragment (e.g. upon screen orientation changes).
   */
  public FlowFragment() {
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater,
                           ViewGroup container,
                           Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.flow, container, false);
    for (Integer photo : photos) {
      ImageView imageView;
      imageView = new ImageView(getActivity());
      imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
      imageView.setPadding(0, 0, 0, 0);
      imageView.setImageResource(photo);
      container.addView(imageView);
    }
    return rootView;
  }

  public void addPhoto(Integer toAdd) {
    this.photos.add(toAdd);
  }

  public void addPhotos(Integer[] photoIds) {
    this.photos.addAll(Arrays.asList(photoIds));
  }
}
