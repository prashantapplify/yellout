package com.example.yellout;

import android.os.Bundle;
import android.R.color;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EnterNumber extends Activity implements OnClickListener {
	Button cancel_btn,go_btn;
	 EditText enter_number;
	 String font_path;
	 TextView num_msg;
	 @Override
		public boolean onTouchEvent(MotionEvent event) {
			// TODO Auto-generated method stub
			 InputMethodManager imm = (InputMethodManager)getSystemService(Context.
	                 INPUT_METHOD_SERVICE);
			 imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
			 return true;
			
		}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_number);
		cancel_btn=(Button)findViewById(R.id.enternumber_cancel);
		go_btn=(Button)findViewById(R.id.enternumber_go);
		go_btn.setOnClickListener(this);
		cancel_btn.setOnClickListener(this);
		enter_number=(EditText)findViewById(R.id.enterphone);
		enter_number.setOnClickListener(this);
		num_msg=(TextView)findViewById(R.id.Enterno__msg);
		font_path="fonts/myriadwebpro-bold.ttf";
		Typeface tf=Typeface.createFromAsset(getAssets(),font_path);
		num_msg.setTypeface(tf);
		enter_number.setTypeface(tf);
		cancel_btn.setTypeface(tf);
		go_btn.setTypeface(tf);
enter_number.setOnKeyListener(new OnKeyListener() {
			
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				
				// TODO Auto-generated method stub
				if(keyCode==KeyEvent.KEYCODE_ENTER)
				{
					if((enter_number.getText().toString().trim().length()>=10)&&(enter_number.getText().toString().trim().length()<=12))
					{
					
							Intent i=new Intent(EnterNumber.this,Verification.class);
							 i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                             i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(i);
							overridePendingTransition(0, 0 );
							finish();
						
					}
					else{
						Toast.makeText(getBaseContext(), "Please Enter Minimum 10 Digits Number", 1000).show();
					}
									}
				else
				{
				return false;
				}
				return true;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.enter_number, menu);
		return true;
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		 Intent i=new Intent(EnterNumber.this,EnterName.class);
		 startActivity(i);
		 overridePendingTransition(0, 0 );
		 finish();
		//super.onBackPressed();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.enternumber_go:
			if((enter_number.getText().toString().trim().length()>=10)&&(enter_number.getText().toString().trim().length()<=12))
			{
				Intent i=new Intent(EnterNumber.this,Verification.class);
				startActivity(i);
				go_btn.setBackgroundResource(color.darker_gray);
				overridePendingTransition(0, 0 );
				finish();
			}
			else{
				Toast.makeText(getBaseContext(), "Please Enter Minimum 10 Digits Number", 1000).show();
			}
			break;
		case R.id.enternumber_cancel:
			Intent i=new Intent(EnterNumber.this,EnterName.class);
			startActivity(i);
			overridePendingTransition(0, 0 );
			finish();
			break;
		
		default:
			break;
		}
	}


}
