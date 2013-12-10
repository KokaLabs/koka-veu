package koka.veu;

import android.app.Fragment;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class PhotoGridActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);

        if (savedInstanceState == null) {
            Fragment newFragment = new PhotosFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.photogridview, newFragment).commit();
        }
    }
}
