package com.example.yellout;

import info.androidhive.tabsswipe.adapter.TabsPagerAdapter;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class Home extends FragmentActivity implements ActionBar.TabListener {

	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	InputMethodManager imm;
	public static String meassge_text="";
	int height;
	int width;
	String font_path;
	//private ActionBar actionBar;
	// Tab titles
	/*private String[] tabs = { "Top Rated", "Games", "Movies" };*/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.home);
		
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		height = displaymetrics.heightPixels;
		width = displaymetrics.widthPixels;
		
		font_path="fonts/myriadwebpro-bold.ttf";
		Typeface tf=Typeface.createFromAsset(getAssets(),font_path);

		imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
		meassge_text="";
		// Initilization
		viewPager = (ViewPager) findViewById(R.id.pager);
		//actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager(),imm,viewPager,height,width,tf);

		viewPager.setAdapter(mAdapter);
		viewPager.setCurrentItem(1);
		/*actionBar.setHomeButtonEnabled(false);*/
		//actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);:		

		/*// Adding Tabs
for (String tab_name : tabs) {
	actionBar.addTab(actionBar.newTab().setText(tab_name)
			.setTabListener(this));
}

/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				//actionBar.setSelectedNavigationItem(position);
				Log.d("tab", position+"");
				imm.hideSoftInputFromWindow(viewPager.getWindowToken(), 0);
				if(position==2){
//					if(meassge_text.trim().length()>0){
						TopRatedFragment.getStatus();
//					}
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// on tab selected
		// show respected fragment view
		Log.d("tab", tab.getPosition()+"");
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		startActivity(intent);
		finish();
	}

}

