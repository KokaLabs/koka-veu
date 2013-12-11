package koka.veu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class FlowFragment extends Fragment {
  private Integer photo;

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
    if (photo != null) {
      ImageView imageView;
      imageView = new SquareImageView(getActivity());
      imageView.setMaxHeight(container.getHeight());
      imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
      imageView.setPadding(0, 0, 0, 0);
      imageView.setImageResource(photo);
      container.addView(imageView);
    }
    return rootView;
  }

  public void addPhoto(Integer toAdd) {
    this.photo = toAdd;
  }
}
