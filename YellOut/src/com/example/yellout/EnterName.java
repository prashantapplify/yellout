package com.example.yellout;

import info.androidhive.tabsswipe.adapter.ImageHelper;
import android.R.color;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnLongClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EnterName extends Activity implements OnClickListener, OnLongClickListener {
	
	String filePath="";
	private int h,w;
	Bitmap bitmap = null;
	ImageHelper yeshelp = new ImageHelper();
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		 InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                 INPUT_METHOD_SERVICE);
		 imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
		 
		 return true;
		
	}

	private static final int SELECT_PICTURE = 1;
	 private String selectedImagePath;
	   private ImageView profile_img;
	   Button cancel_btn,go_btn;
	   Bitmap bmp;
	   EditText enter_name;
	   String a,font_path;
	   
	  int l;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_name);
		
		Display display = getWindowManager().getDefaultDisplay(); 
		w = display.getWidth();    
		h = display.getHeight();
		
		profile_img=(ImageView)findViewById(R.id.entername_profilepic);
		cancel_btn=(Button)findViewById(R.id.entername_cancel);
		go_btn=(Button)findViewById(R.id.entername_go);
		go_btn.setOnClickListener(this);
		cancel_btn.setOnClickListener(this);
		go_btn.setOnLongClickListener(this);
		enter_name=(EditText)findViewById(R.id.entername);
		enter_name.setOnClickListener(this);
		font_path="fonts/myriadwebpro-bold.ttf";
		TextView name_msg=(TextView)findViewById(R.id.Entername_msg);
		Typeface tf=Typeface.createFromAsset(getAssets(),font_path);
		name_msg.setTypeface(tf);
		enter_name.setTypeface(tf);
		cancel_btn.setTypeface(tf);
		go_btn.setTypeface(tf);
		enter_name.setOnKeyListener(new OnKeyListener() {
			
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				
				// TODO Auto-generated method stub
				if(keyCode==KeyEvent.KEYCODE_ENTER)
				{
						Intent i=new Intent(EnterName.this,EnterNumber.class);
						 i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                         i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(i);
						overridePendingTransition(0, 0 );
						finish();
					
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
		getMenuInflater().inflate(R.menu.enter_name, menu);
		return true;
	}
		
		public void gallery(View v) {
			Intent intent = new Intent();
			intent.setType("image/*");
			intent.setAction(Intent.ACTION_GET_CONTENT);//
			startActivityForResult(Intent.createChooser(intent, "Select Picture"),SELECT_PICTURE);
			
		}
		@SuppressWarnings("deprecation")
		public void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if (resultCode == RESULT_OK) {
	            if (requestCode == SELECT_PICTURE) {
	                Uri selectedImageUri = data.getData();
	                selectedImagePath=selectedImageUri.toString();
	                filePath = getPath(selectedImageUri);
	               // filePath = getPath(selectedImageUri);
					if (filePath != null) {
						decodeFile(filePath);

						Bitmap p = yeshelp.getRoundedShape(bitmap,w,h);
						
						profile_img.setBackgroundDrawable(new BitmapDrawable(getResources(), p));
					} else {
						bitmap = null;
					}
	                
	              /* // Toast.makeText(getBaseContext(), selectedImagePath, Toast.LENGTH_LONG).show();
	               File img=new File(selectedImagePath);
	                FileInputStream fis=null;
	                try{
	                	 fis = new FileInputStream(img);
	                }
	                catch(Exception e)
	                {
	                	e.printStackTrace();
	                }
	                try {
						bmp=BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImageUri));
						//Toast.makeText(getBaseContext(), bmp.toString(), 1000).show();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	               
	                ByteArrayOutputStream baos = new ByteArrayOutputStream();  
	               bmp.compress(Bitmap.CompressFormat.JPEG, 100 , baos);    
	               byte[] b = baos.toByteArray();
	                	  profile_img.setBackgroundDrawable(new BitmapDrawable(getResources(), bmp));*/
	               
	            }
	        }
	    }

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.entername_go:
				if(enter_name.getText().toString().trim().length()>0)
				{
					Intent i=new Intent(getBaseContext(),EnterNumber.class);
					startActivity(i);
					overridePendingTransition(0, 0 );
					go_btn.setBackgroundResource(color.darker_gray);
					finish();
					
				}
				else
				{
					Toast.makeText(getBaseContext(), "Please Enter Your Name", Toast.LENGTH_LONG).show();
					
				}
				break;
				case R.id.entername_cancel:
					Intent i=new Intent(getBaseContext(),Startpage.class);
					startActivity(i);
					cancel_btn.setBackgroundResource(color.darker_gray);
					overridePendingTransition(0, 0 );
					finish();
					break;
				
			default:
				break;
			}
		}

		@SuppressWarnings("deprecation")
		public String getPath(Uri uri) {
			Cursor cursor = null;
			int column_index = 0;
			try {
				String[] projection = { MediaStore.Images.Media.DATA };
				cursor = managedQuery(uri, projection, null, null, null);
				column_index = cursor
						.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				cursor.moveToFirst();

			} catch (Exception e) {
				// TODO: handle exception
			}
			return cursor.getString(column_index);
		}

		public void decodeFile(String filePath) {
			// Decode image size
			try {
				ExifInterface exif = new ExifInterface(filePath);
				int orientation = exif.getAttributeInt(
						ExifInterface.TAG_ORIENTATION,
						ExifInterface.ORIENTATION_NORMAL);

				int angle = 0;

				if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
					angle = 90;
				} else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
					angle = 180;
				} else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
					angle = 270;
				}

				final	Matrix mat = new Matrix();
				mat.postRotate(angle);

				BitmapFactory.Options o = new BitmapFactory.Options();
				o.inJustDecodeBounds = true;
				BitmapFactory.decodeFile(filePath, o);

				// The new size we want to scale to
				

				// Find the correct scale value. It should be the power of 2.
				int width_tmp = o.outWidth, height_tmp = o.outHeight;
				int scale = 1;
				while (true) {
					if (width_tmp < w*2 && height_tmp < h*2)
						break;
					width_tmp /= 2;
					height_tmp /= 2;
					scale *= 2;
				}

				// Decode with inSampleSize
				int scale1 = 1;
				Bitmap check=null;
				try{
					int www=(int) (w/1.5);
					int hhh=(int) (h/3.5);
					// Find the correct scale value. It should be the power of 2.
					int width_tmp1 = o.outWidth, height_tmp1 = o.outHeight;
					while (true) {
						if (width_tmp1 < www*2 && height_tmp1 < hhh*2)
						{
							break;
						}
						/*else if(width_tmp1>1700 || height_tmp1>2000)
						{
							width_tmp1 /= 5;
							height_tmp1 /= 5;
							scale1 *= 5;	
						}*/
						else{
						width_tmp1 /= 2;
						height_tmp1 /= 2;
						scale1 *= 2;
						}
					}
				}
				catch(Exception e)
				{
					
				}
				BitmapFactory.Options o2 = new BitmapFactory.Options();
				o2.inSampleSize = scale1;

				final BitmapFactory.Options o3 = new BitmapFactory.Options();
				o3.inSampleSize = scale;
				try {

					bitmap = BitmapFactory.decodeFile(filePath, o2);
					bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
							bitmap.getHeight(), mat, true);
					
					

				} catch (Exception e) {
					
					
				}
			} catch (Exception e) {
				// TODO: handle exception
				
			}

		}
		@Override
		public boolean onLongClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.entername_go:
				go_btn.setBackgroundResource(color.background_dark);
				break;

			default:
				break;
			}
			return false;
		}
		@Override
		public void onBackPressed() {
			// TODO Auto-generated method stub
			super.onBackPressed();
			Intent i=new Intent(this,Startpage.class);
			startActivity(i);
			overridePendingTransition(0, 0 );
			finish();
		}
		
}
