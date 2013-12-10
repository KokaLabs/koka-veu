package koka.veu;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

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
  public void onItemSelected(String id) {
    if (mTwoPane) {
      // In two-pane mode, show the detail view in this activity by
      // adding or replacing the detail fragment using a
      // fragment transaction.
      Bundle arguments = new Bundle();
      arguments.putString(ItemDetailFragment.ARG_ITEM_ID, id);
      ItemDetailFragment fragment = new ItemDetailFragment();
      fragment.setArguments(arguments);
      getSupportFragmentManager().beginTransaction()
          .replace(R.id.item_detail_container, fragment)
          .commit();

    } else {
      // In single-pane mode, simply start the detail activity
      // for the selected item ID.
      Intent detailIntent = new Intent(this, ItemDetailActivity.class);
      detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_ID, id);
      startActivity(detailIntent);
    }
  }
}
