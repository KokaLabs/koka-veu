package koka.veu;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class GalleryMultiChoiceListener
    implements GridView.MultiChoiceModeListener {
  private List<Integer> selectedPhotos = new ArrayList<Integer>();
  private final PhotosFragment.Callbacks mCallbacks;

  public GalleryMultiChoiceListener(PhotosFragment.Callbacks mCallbacks) {
    this.mCallbacks = mCallbacks;
  }

  @Override
  public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
    MenuInflater inflater = actionMode.getMenuInflater();
    inflater.inflate(R.menu.gallery_multiselect, menu);
    return true;
  }

  @Override
  public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
    return false;
  }

  @Override
  public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
    switch (menuItem.getItemId()) {
      case R.id.gallery_multiselect_confirm:
        mCallbacks.onPhotosSelected(selectedPhotos);
        break;
      default:
        throw new IllegalArgumentException("menu selection not supported");
    }
    actionMode.finish();
    return false;
  }

  public void onItemCheckedStateChanged(
      ActionMode mode, int position, long id, boolean checked) {
    selectedPhotos.add(PhotoImageAdapter.getItemAt(position));
    mode.setSubtitle("" + selectedPhotos.size() + " items selected");
  }

  @Override
  public void onDestroyActionMode(ActionMode actionMode) {
    selectedPhotos.clear();
  }
}