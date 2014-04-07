package com.example.yahoonewsreader;

import com.example.yahoonewsreader.fragment.NewsFragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivityFragment extends MainActivityUI {

	public static final String FEED = "FEED";
	public static final String FEED_MAIN = "";
	public static final String FEED_ENTERTAINMENT = "/entertainment";
	public static final String FEED_SPORTS = "/sports";

	private static final int FRAGMENT_MAIN = 0;
	private static final int FRAGMENT_ENTERTAINMENT = 1;
	private static final int FRAGMENT_SPORTS = 2;
	private static final int FRAGMENT_COUNT = FRAGMENT_SPORTS + 1;
	
	Fragment[] fragments = new Fragment[FRAGMENT_COUNT];
	String[] actionBarTitles = new String[] { "Main Feed",
											  "Entertainment",
											  "Sports" };
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	initFragments();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private void initFragments() {
    	sliding_menu_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				switch (position) {
				case 0:
					showFragment(FRAGMENT_MAIN, false);
					break;
				case 1:
					showFragment(FRAGMENT_ENTERTAINMENT, false);
					break;
				case 2:
					showFragment(FRAGMENT_SPORTS, false);
					break;
				default:
					break;
				}
				sliding_menu.toggle();
			}
		});
    	
    	Bundle bundle = new Bundle();
    	bundle.putString(FEED, FEED_MAIN);
    	fragments[FRAGMENT_MAIN] = new NewsFragment();
    	fragments[FRAGMENT_MAIN].setArguments(bundle);
    	
    	bundle = new Bundle();
    	bundle.putString(FEED, FEED_ENTERTAINMENT);
    	fragments[FRAGMENT_ENTERTAINMENT] = new NewsFragment();
    	fragments[FRAGMENT_ENTERTAINMENT].setArguments(bundle);
    	
    	bundle = new Bundle();
    	bundle.putString(FEED, FEED_SPORTS);
    	fragments[FRAGMENT_SPORTS] = new NewsFragment();
    	fragments[FRAGMENT_SPORTS].setArguments(bundle);
    	
    	addFragments();
    	hideFragments();
    	showFragment(FRAGMENT_MAIN, false);
    }
    
    private void addFragments() {
    	FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();

		for (int i = 0; i < FRAGMENT_COUNT; i++) {

			transaction
					.add(R.id.frame, fragments[i], String.valueOf(i));
		}

		transaction.commit();
    }
    
	private void hideFragments() {
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		for (int i = 0; i < FRAGMENT_COUNT; i++) {
			transaction.hide(fragments[i]);
		}

		transaction.commit();
	}
    
	private void showFragment(int fragmentIndex, boolean addToBackStack) {

		// update the main content by replacing fragments
		FragmentManager fm = getFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		for (int i = 0; i < FRAGMENT_COUNT; i++) {
			if (i == fragmentIndex) {
				transaction.show(fragments[i]);
			} else if (fragments[i].isVisible()) {
				transaction.hide(fragments[i]);
			}
		}
		if (addToBackStack) {
			transaction.addToBackStack(null);
		}
		transaction.commit();

		getActionBar().setTitle(actionBarTitles[fragmentIndex]);

	}
    
    
}
