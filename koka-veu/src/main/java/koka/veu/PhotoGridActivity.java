package koka.veu;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import java.util.List;

public class PhotoGridActivity extends FragmentActivity
    implements PhotosFragment.Callbacks {
  private boolean mTwoPane;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.gallery);

    mTwoPane = findViewById(R.id.flow_container) != null;

    FragmentManager fragMan = getFragmentManager();
    if (savedInstanceState == null) {
      PhotosFragment grid = mTwoPane
          ? new PhotosFragment.TwoPanePhotosFragment()
          : new PhotosFragment.SinglePanePhotosFragment();
      FragmentTransaction ft = fragMan.beginTransaction();
      ft.add(R.id.photogrid, grid).commit();
    }
  }

  @Override
  public void onPhotoSelected(Integer photoId) {
    if (mTwoPane) {
      FlowFragment fragment = new FlowFragment();
      fragment.addPhoto(photoId);
      getSupportFragmentManager().beginTransaction()
          .replace(R.id.flow_container, fragment)
          .commit();

    } else {
      // In single-pane mode, simply start the detail activity
      // for the selected item ID.
//      Intent detailIntent = new Intent(this, ItemDetailActivity.class);
//      detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_ID, id);
//      startActivity(detailIntent);
    }
  }

  @Override
  public void onPhotosSelected(List<Integer> photoIds) {
    if (mTwoPane) {
      FlowFragment fragment = new FlowFragment();
      fragment.addPhotos(photoIds);
      getSupportFragmentManager().beginTransaction()
          .replace(R.id.flow_container, fragment)
          .commit();
    }
  }
}
