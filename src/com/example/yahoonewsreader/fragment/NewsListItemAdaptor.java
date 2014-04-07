package com.example.yahoonewsreader.fragment;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yahoonewsreader.R;
import com.nostra13.universalimageloader.core.ImageLoader;

public class NewsListItemAdaptor extends BaseAdapter {

	Activity activity;
	ArrayList<HashMap<String, Object>> data;
	private static LayoutInflater inflater=null;
	
	public NewsListItemAdaptor(Activity activity, ArrayList<HashMap<String, Object>> data) {
		this.activity = activity;
		this.data = data;
		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi=convertView;
        if(convertView==null) {
            vi = inflater.inflate(R.layout.list_item, null);
        }
        ImageLoader imageLoader = ImageLoader.getInstance();
        
        TextView title = (TextView) vi.findViewById(R.id.title);
        title.setText(data.get(position).get(NewsFragment.KEY_TITLE).toString());
        ImageView image = (ImageView) vi.findViewById(R.id.image);
        if (data.get(position).get(NewsFragment.KEY_MEDIA) != null) {
        	imageLoader.displayImage(data.get(position).get(NewsFragment.KEY_MEDIA).toString(), image);
        }
		return vi;
	}
}
