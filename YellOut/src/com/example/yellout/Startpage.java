package com.example.yellout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Startpage extends Activity {
	Button start_one;
	String font_path;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_startpage);
		start_one=(Button)findViewById(R.id.start);
		font_path="fonts/myriadwebpro-bold.ttf";
		Typeface tf=Typeface.createFromAsset(getAssets(),font_path);
		start_one.setTypeface(tf);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.startpage, menu);
		return true;
	}
	public void move(View v) {
		Intent i=new Intent(this,EnterName.class);
		startActivity(i);
		overridePendingTransition(0, 0 );
		finish();
	}

	
	

}
