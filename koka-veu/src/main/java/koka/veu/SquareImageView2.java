package koka.veu;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class SquareImageView2 extends ImageView {
  public SquareImageView2(final Context context) {
    super(context);
  }

  public SquareImageView2(final Context context, final AttributeSet attrs) {
    super(context, attrs);
  }

  public SquareImageView2(final Context context, final AttributeSet attrs, final int defStyle) {
    super(context, attrs, defStyle);
  }


  @Override
  protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
    final int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
    setMeasuredDimension(width, width);
  }

  @Override
  protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh) {
    super.onSizeChanged(w, w, oldw, oldh);
  }
}