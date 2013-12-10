package koka.veu;

import android.app.Fragment;
import android.os.Bundle;
import android.widget.GridView;

public class PhotosFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GridView gridview = (GridView) this.getActivity().findViewById(R.id.photogridview);
        gridview.setAdapter(new PhotoImageAdapter(this.getActivity()));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}