package com.example.yellout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Verification extends Activity implements OnClickListener {
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent i=new Intent(Verification.this,EnterNumber.class);
		startActivity(i);
		overridePendingTransition(0, 0 );
		finish();
		
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		 InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                 INPUT_METHOD_SERVICE);
		 imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
		 return true;
		
	}
	TextView pin,cancel;
	Button confirm_button,resend_button;
	EditText v1,v2,v3,v4;
	String font_path;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_verification);
		font_path="fonts/myriadwebpro-bold.ttf";
		Typeface tf=Typeface.createFromAsset(getAssets(),font_path);
		pin=(TextView)findViewById(R.id.vreify_enterpin);
		cancel=(TextView)findViewById(R.id.verify_cancel);
		confirm_button=(Button)findViewById(R.id.vreify_confirm);
		confirm_button.setEnabled(false);
		confirm_button.setOnClickListener(this);
		resend_button=(Button)findViewById(R.id.verfiy_resend);
		v1=(EditText)findViewById(R.id.verifi_1);
		v2=(EditText)findViewById(R.id.verifi_2);
		v3=(EditText)findViewById(R.id.verifi_3);
		v4=(EditText)findViewById(R.id.verifi_4);
		pin.setTypeface(tf);
		cancel.setTypeface(tf);
		confirm_button.setTypeface(tf);
		resend_button.setTypeface(tf);
		v1.setTypeface(tf);
		v2.setTypeface(tf);
		v3.setTypeface(tf);
		v4.setTypeface(tf);
		cancel.setOnClickListener(this);
		v1.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if(v1.getText().toString().length()==1)
				{
					v2.requestFocus();
				}
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
		v2.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if(v1.getText().toString().length()==1)
				{
					v3.requestFocus();
				}
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
		v3.addTextChangedListener(new TextWatcher() {
	
	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		if(v1.getText().toString().length()==1)
		{
			v4.requestFocus();
		}
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
		v4.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if(v1.getText().toString().length()==1)
				{
					confirm_button.setEnabled(true);
					
					
						 Intent i=new Intent(Verification.this,Home.class);
						 i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                         i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						 startActivity(i);
						 overridePendingTransition(0, 0 );
						 finish();
					
				}
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
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.verification, menu);
		return true;
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.vreify_confirm:
			 Intent i=new Intent(Verification.this,Home.class);
			 startActivity(i);
			 overridePendingTransition(0, 0 );
			 finish();
			break;
		case R.id.verify_cancel:
			Intent ii=new Intent(Verification.this,EnterNumber.class);
			startActivity(ii);
			overridePendingTransition(0, 0 );
			finish();
			break;
		default:
			break;
		}
	}
	

}
