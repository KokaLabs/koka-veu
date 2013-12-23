package koka.veu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class PhotoImageAdapter extends BaseAdapter {
  private Context mContext;

  public PhotoImageAdapter(Context c) {
    mContext = c;
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
    ImageView imageView;
    if (convertView == null) {  // if it's not recycled, initialize some attributes
      imageView = new SquareImageView(mContext);
      imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
      imageView.setPadding(0, 0, 0, 0);
    } else {
      imageView = (ImageView) convertView;
    }

    imageView.setImageResource(mThumbIds[position]);
    return imageView;
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
}