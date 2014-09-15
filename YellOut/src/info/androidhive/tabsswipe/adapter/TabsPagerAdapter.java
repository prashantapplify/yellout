package info.androidhive.tabsswipe.adapter;

import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.yellout.GamesFragment;
import com.example.yellout.MoviesFragment;
import com.example.yellout.TopRatedFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	InputMethodManager imm;
	ViewPager viewPager;
	String meassge_text;
	int height,width;
	Typeface tf;
	public TabsPagerAdapter(FragmentManager fm, InputMethodManager imm, ViewPager viewPager,int height,int width, Typeface tf) {
		
		super(fm);
		this.imm=imm;
		this.viewPager=viewPager;
		this.height=height;
		this.width=width;
		this.tf=tf;
		
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// Top Rated fragment activity
			return new MoviesFragment(imm,tf);
			
		case 1:
			// Games fragment activity
			return new GamesFragment(imm,tf);
		case 2:
			// Movies fragment activity
			return new TopRatedFragment(viewPager,meassge_text,height,width,tf);
		}

		return null;
	}

	
	public int getCount() {
		// get item count - equal to number of tabs
		return 3;
	}

}
