package koka.veu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;

public class PhotoImageAdapter extends BaseAdapter {
  private final LayoutInflater mInflater;
  private final Context mContext;

  public PhotoImageAdapter(Context c) {
    mContext = c;
    mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  @Override
  public int getCount() {
    return mThumbIds.length;
  }

  @Override
  public Object getItem(int position) {
    return getItemAt(position);
  }

  public static Integer getItemAt(int position) {
    return mThumbIds[position];
  }

  @Override
  public long getItemId(int position) {
    return 0;
  }



  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    GalleryItem wrapper;
    if (convertView == null) {  // if it's not recycled, initialize some attributes
      wrapper = new GalleryItem();
      convertView = mInflater.inflate(R.layout.gallery_item, null);
      wrapper.checkbox = (CheckBox) convertView.findViewById(R.id.checkmark_icon_32);
      wrapper.img = (ImageView) convertView.findViewById(R.id.gallery_item_img);
//      ImageView imageView = new SquareImageView(mContext);
      wrapper.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
      wrapper.img.setPadding(0, 0, 0, 0);
      convertView.setTag(wrapper);
    } else {
      wrapper = (GalleryItem) convertView.getTag();
    }
    wrapper.img.setId(position);
    wrapper.img.setImageResource(mThumbIds[position]);
    wrapper.checkbox.setId(position);
    wrapper.checkbox.setChecked(false);
    wrapper.id = position;
    return convertView;
  }

  private static Integer[] mThumbIds = {
      R.drawable.sample_2, R.drawable.sample_3,
      R.drawable.sample_4, R.drawable.sample_5,
      R.drawable.sample_6, R.drawable.sample_7,
      R.drawable.sample_0, R.drawable.sample_1,
      R.drawable.sample_2, R.drawable.sample_3,
      R.drawable.sample_4, R.drawable.sample_5,
      R.drawable.sample_6, R.drawable.sample_7,
      R.drawable.sample_0, R.drawable.sample_1,
      R.drawable.sample_2, R.drawable.sample_3,
      R.drawable.sample_4, R.drawable.sample_5,
      R.drawable.sample_6, R.drawable.sample_7
  };

  private static final class GalleryItem {
    ImageView img;
    CheckBox checkbox;
    int id;
  }
}