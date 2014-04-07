package com.example.yahoonewsreader;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.util.Linkify;
import android.view.Menu;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.example.yahoonewsreader.fragment.NewsFragment;
import com.example.yahoonewsreader.views.ObservableScrollView;
import com.example.yahoonewsreader.views.ParallaxImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

public class Article extends Activity {

	HashMap<String, Object> data;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_article);
		getActionBar().hide();
		
		data = (HashMap<String, Object>)getIntent().getSerializableExtra("KEY");
		System.out.println(data.toString());
		
		setBackdrop();
		setArticle();
	}

	private void setBackdrop() {
		ObservableScrollView scroller = (ObservableScrollView) findViewById(R.id.scrollview);
        final ParallaxImageView backdrop = (ParallaxImageView) scroller.findViewById(R.id.image);
        backdrop.setScaleType(ScaleType.CENTER_CROP);
        scroller.setScrollViewListener(new ObservableScrollView.ObservableScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                backdrop.setTranslation(y);
            }
        });
        
        ImageLoader imageLoader = ImageLoader.getInstance();
        
        String url = data.get(NewsFragment.KEY_MEDIA).toString();
        String mask = "http://l1.yimg.com/bt/api/res/1.2/1nwzVAbwWRrcVKuw5sMSVQ--/YXBwaWQ9eW5ld3M7Zmk9ZmlsbDtoPTg2O3E9NzU7dz0xMzA-/";
        imageLoader.displayImage(url.substring(mask.length()), backdrop);
	}
	
	private void setArticle() {
		TextView title = (TextView) findViewById(R.id.title);
		title.setText(data.get(NewsFragment.KEY_TITLE).toString());
		
		TextView date = (TextView) findViewById(R.id.date);
		date.setText(data.get(NewsFragment.KEY_DATE).toString());
		
		TextView link = (TextView) findViewById(R.id.link);
		link.setText("Read more: " + data.get(NewsFragment.KEY_LINK).toString());
		Linkify.addLinks(link, Linkify.ALL);
		
		TextView description = (TextView) findViewById(R.id.description);
		description.setText(Html.fromHtml(data.get(NewsFragment.KEY_DESCRIPTION).toString()));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.article, menu);
		return true;
	}

}
