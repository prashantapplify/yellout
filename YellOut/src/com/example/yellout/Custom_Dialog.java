package com.example.yellout;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class Custom_Dialog extends Activity{

	LinearLayout custonDialog_main_lay;
	int w,h;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	     WindowManager.LayoutParams wmlp = this.getWindow().getAttributes();
	     this.getWindow().setBackgroundDrawable(new BitmapDrawable());
	 wmlp.gravity = Gravity.CENTER ;
		setContentView(R.layout.custom_dialog);
		
		Display display = getWindowManager().getDefaultDisplay();
		w=display.getWidth();
		h=display.getHeight();
		
		custonDialog_main_lay=(LinearLayout)findViewById(R.id.custonDialog_main_lay);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w-100, (h/3)-20);
		custonDialog_main_lay.setLayoutParams(params);
		
	}
	
	

}
