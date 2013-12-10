package koka.veu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import koka.veu.dummy.DummyContent;

public class FlowFragment extends Fragment {
  private View photo;

  /**
   * Mandatory empty constructor for the fragment manager to instantiate the
   * fragment (e.g. upon screen orientation changes).
   */
  public FlowFragment() {}

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater,
                           ViewGroup container,
                           Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_flow_item, container, false);
    if (photo != null) {
      ((TextView) rootView.findViewById(R.id.flow_item)).setText(photo.toString());
    }
    return rootView;
  }

  public void addPhoto(View toAdd) {
    this.photo = toAdd;
  }
}
