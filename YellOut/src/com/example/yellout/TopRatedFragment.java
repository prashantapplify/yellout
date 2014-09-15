package com.example.yellout;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class TopRatedFragment extends Fragment implements OnClickListener {

	static TextView contact_back;
	static TextView contact_contacts;
	static TextView contact_send;
	ViewPager viewPager;
	String meassge_text;
	LinearLayout l1,l2,l3,l4;
	int height1;
	int width1;
	TextView t1,t2,t3,t4,t5,t6,t7,t8;
	Typeface tf;
	
	public TopRatedFragment(ViewPager viewPager, String meassge_text,int height,int width, Typeface tf) {
		// TODO Auto-generated constructor stub
		this.viewPager=viewPager;
		this.meassge_text=meassge_text;
		this.height1=height;
		this.width1=width;
		this.tf=tf;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_top_rated, container, false);
		
		contact_back=(TextView)rootView.findViewById(R.id.contact_back);
		contact_contacts=(TextView)rootView.findViewById(R.id.contact_contacts);
		contact_send=(TextView)rootView.findViewById(R.id.contact_send);
		contact_contacts.setOnClickListener(this);
		contact_send.setOnClickListener(this);
		contact_back.setOnClickListener(this);
		
		l1=(LinearLayout)rootView.findViewById(R.id.linear_1);
		l2=(LinearLayout)rootView.findViewById(R.id.linear_2);
		l3=(LinearLayout)rootView.findViewById(R.id.linear_3);
		l4=(LinearLayout)rootView.findViewById(R.id.linear_4);
		
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, height1/4);
		l1.setLayoutParams(layoutParams);
		l2.setLayoutParams(layoutParams);
		l3.setLayoutParams(layoutParams);
		l4.setLayoutParams(layoutParams);
		t1=(TextView)rootView.findViewById(R.id.top_t1);
		t2=(TextView)rootView.findViewById(R.id.top_t2);
		t3=(TextView)rootView.findViewById(R.id.top_t3);
		t4=(TextView)rootView.findViewById(R.id.top_t4);
		t5=(TextView)rootView.findViewById(R.id.top_t5);
		t6=(TextView)rootView.findViewById(R.id.top_t6);
		t7=(TextView)rootView.findViewById(R.id.top_t7);
		t8=(TextView)rootView.findViewById(R.id.top_t8);
		t1.setTypeface(tf);
		t2.setTypeface(tf);
		t3.setTypeface(tf);
		t4.setTypeface(tf);
		t5.setTypeface(tf);
		t6.setTypeface(tf);
		t7.setTypeface(tf);
		t8.setTypeface(tf);				
		return rootView;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.contact_back:
			viewPager.setCurrentItem(1);
			break;

		default:
			break;
		}
	}
	public static void getStatus() {
		// TODO Auto-generated method stub
		Log.d("++++", "+++++");
		if(Home.meassge_text.trim().length()>0){
			contact_send.setVisibility(View.VISIBLE);
			contact_back.setVisibility(View.VISIBLE);
			contact_contacts.setVisibility(View.VISIBLE);
		}else if(Home.meassge_text.trim().length()==0){
			contact_send.setVisibility(View.GONE);
			contact_back.setVisibility(View.GONE);
			contact_contacts.setVisibility(View.VISIBLE);
		}
	}
}
