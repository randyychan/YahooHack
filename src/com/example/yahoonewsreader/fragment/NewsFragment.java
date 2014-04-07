package com.example.yahoonewsreader.fragment;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.yahoonewsreader.Article;
import com.example.yahoonewsreader.MainActivityFragment;
import com.example.yahoonewsreader.rss.RSSReader;


public class NewsFragment extends Fragment {

    public static final String BASE_URL = "http://news.yahoo.com/rss/";
    public static final String KEY_TITLE = "title";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_LINK = "link";
    public static final String KEY_DATE = "pubDate";
    public static final String KEY_SOURCE = "source";
    public static final String KEY_MEDIA = "media:content";
    
    public String subfeed = null;
    RSSReader reader = new RSSReader();
	ListView newsList;
	NewsListItemAdaptor adapter = null;
	ArrayList<String> lists = new ArrayList<String>();
	ArrayList<HashMap<String, Object>> post_lists = new ArrayList<HashMap<String, Object>>();
	
	public NewsFragment() {

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Bundle bundle = getArguments();
		subfeed = bundle.getString(MainActivityFragment.FEED);
		newsList = new ListView(getActivity());
		
		newsList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				Intent intent = new Intent(getActivity(), Article.class);
				intent.putExtra("KEY", post_lists.get(position));
				startActivity(intent);
			}
		});
		
		return newsList;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	     adapter = new NewsListItemAdaptor(getActivity(), post_lists);

	     newsList.setAdapter(adapter);
		
		new DownloadFeed().execute(new Void[] {});
	}
	
	private class DownloadFeed extends AsyncTask<Void, Void, Void> {
	     protected Void doInBackground(Void...voids) {
	    	 Document xmlFeed = reader.getRSSFromServer(BASE_URL+subfeed);
	    	 NodeList nodes = xmlFeed.getElementsByTagName("item");
	    	 for (int i = 0; i < nodes.getLength(); i++) {
	 			Element item = (Element) nodes.item(i);
	 			HashMap<String, Object> feed = new HashMap<String, Object>();
	 			feed.put(KEY_TITLE, reader.getValue(item, KEY_TITLE));
	 			feed.put(KEY_DESCRIPTION, reader.getValue(item, KEY_DESCRIPTION));
	 			feed.put(KEY_DATE, reader.getValue(item, KEY_DATE));
	 			feed.put(KEY_LINK, reader.getValue(item, KEY_LINK));
	 			feed.put(KEY_MEDIA, reader.getImageURL(item, KEY_MEDIA));
	 			post_lists.add(feed);
	 			lists.add(feed.get(KEY_TITLE).toString());
	 		}
	         return null;
	     }
	     
	  protected void onPostExecute(Void result) {
	    	 adapter.notifyDataSetChanged();
	     }
	 }




}
