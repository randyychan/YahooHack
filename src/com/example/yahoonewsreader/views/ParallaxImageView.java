package com.example.yahoonewsreader.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

public class ParallaxImageView extends ImageView {
    protected int mTranslation = 0;
 
    public ParallaxImageView(final Context context)
    {
        super(context);
    }
 
    public ParallaxImageView(final Context context, final AttributeSet attrs)
    {
        super(context, attrs);
    }
 
    public ParallaxImageView(final Context context, final AttributeSet attrs, final int defStyle)
    {
        super(context, attrs, defStyle);
    }
 
    public void setTranslation(int translation) {
        mTranslation = translation;
        invalidate();
    }
 
    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.translate(0, mTranslation/2);
        super.draw(canvas);
        canvas.restore();
    }
}