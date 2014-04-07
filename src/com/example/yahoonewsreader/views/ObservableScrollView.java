package com.example.yahoonewsreader.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class ObservableScrollView extends ScrollView {
	 
    private ObservableScrollViewListener mScrollViewListener = null;
 
    public interface ObservableScrollViewListener {
        void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy);
    }
 
    public ObservableScrollView(Context context) {
        super(context);
    }
 
    public ObservableScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
 
    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
 
    public void setScrollViewListener(ObservableScrollViewListener scrollViewListener) {
        mScrollViewListener = scrollViewListener;
    }
 
    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (mScrollViewListener != null) {
            mScrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }
}