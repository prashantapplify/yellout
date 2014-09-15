package com.example.yellout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class GamesFragment extends Fragment implements OnClickListener {
	LinearLayout mainlinear;
	EditText meassge_text;
	int click_text=0;
	InputMethodManager imm;
	Typeface tf;
	public GamesFragment(InputMethodManager imm, Typeface tf) {
		// TODO Auto-generated constructor stub
		this.imm=imm;
		this.tf=tf;
		
		
	}

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_games, container, false);
		
		
		
		meassge_text=(EditText)rootView.findViewById(R.id.meassge_text);
		meassge_text.setTypeface(tf);
		meassge_text.setSelection(0);
		meassge_text.setOnClickListener(this);
		meassge_text.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				Home.meassge_text=meassge_text.getText().toString().trim();
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		//message_cencel=(TextView)rootView.findViewById(R.id.message_cencel);
		
		mainlinear=(LinearLayout)rootView.findViewById(R.id.main_linear);
		mainlinear.setOnClickListener(this);
		return rootView;
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.meassge_text:
			Log.d("+++++", meassge_text.getText().toString());
			if(click_text==0)
			{
				click_text=1;
			}
			else{
				click_text=0;
				imm.hideSoftInputFromWindow(meassge_text.getWindowToken(), 0);
			}
			break;
			
		/*case R.id.message_cencel:
			imm.hideSoftInputFromWindow(message_cencel.getWindowToken(), 0);
			message_cencel.setVisibility(View.GONE);
			break;*/
		case R.id.main_linear:
			imm.hideSoftInputFromWindow(mainlinear.getWindowToken(), 0);
			
			break;
		}
	}
}
