package com.example.yahoonewsreader;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.yahoonewsreader.fragment.NewsFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;

public class MainActivityUI extends SlidingActivity {
	
	ListView sliding_menu_list; 
	SlidingMenu sliding_menu;
	
    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		setBehindContentView(R.layout.left_drawer);

        initUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private void initUI() {
    	sliding_menu_list = (ListView) findViewById(R.id.left_drawer);
    	sliding_menu = getSlidingMenu();
    	
    	initSlidingMenu();
    }
    

    private void initSlidingMenu() {
    	sliding_menu.setMode(SlidingMenu.LEFT);
		sliding_menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		sliding_menu.setShadowWidthRes(R.dimen.shadow_width);
		sliding_menu.setShadowDrawable(R.drawable.shadow);
		sliding_menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sliding_menu.setFadeDegree(0.35f);
    	
    	String[] sliding_menu_items = getResources().getStringArray(R.array.sliding_menu_strings);
    	sliding_menu_list.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, sliding_menu_items));
    	
    } 
}
