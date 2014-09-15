package com.example.yellout;

import info.androidhive.tabsswipe.adapter.ImageHelper;

import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
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
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class options extends Activity implements OnClickListener{
	public static Bitmap p ;
	LinearLayout approx_lay;
	Uri selectedImageUri ;
	Uri imageUri;
	TextView Invite_mail,Invite_sms,Invite_cancel;
	String filePath="";
	private int h,w;
	Bitmap bitmap = null;
	ImageHelper yeshelp = new ImageHelper();
	private static final int SELECT_PICTURE = 1;
	private static final int PICK_Camera_IMAGE= 2;
	private String selectedImagePath,font_path;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	     WindowManager.LayoutParams wmlp = this.getWindow().getAttributes();
	     this.getWindow().setBackgroundDrawable(new BitmapDrawable());
	 wmlp.gravity = Gravity.BOTTOM ;
	 font_path="fonts/myriadwebpro-bold.ttf";
		setContentView(R.layout.options);
		
		Display display = getWindowManager().getDefaultDisplay(); 
		w = display.getWidth();
		h = display.getHeight();
		
		approx_lay=(LinearLayout)findViewById(R.id.approx_lay);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w-30, (h/3)-20);
		approx_lay.setLayoutParams(params);
		
		Invite_mail=(TextView)findViewById(R.id.options_camera);
		Invite_mail.setOnClickListener(this);
		Typeface tf=Typeface.createFromAsset(getAssets(),font_path);
		Invite_mail.setTypeface(tf);
		Invite_sms=(TextView)findViewById(R.id.options_gallery);
		Invite_sms.setOnClickListener(this);
		Invite_cancel=(TextView)findViewById(R.id.options_cancel);
		Invite_cancel.setOnClickListener(this);
		Invite_sms.setTypeface(tf);
		Invite_cancel.setTypeface(tf);
				
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.options_camera:
			String fileName = "new-photo-name.jpg";
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, fileName);
            values.put(MediaStore.Images.Media.DESCRIPTION,
                            "Image capture by camera");
            imageUri = getContentResolver().insert(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            values);
            // create new Intent
            Intent intent = new Intent(
                            MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            startActivityForResult(intent, PICK_Camera_IMAGE);
			
			break;
			
		case R.id.options_gallery:
		Intent intentt = new Intent();
			intentt.setType("image/*");
			intentt.setAction(Intent.ACTION_GET_CONTENT);//
			startActivityForResult(Intent.createChooser(intentt, "Select Picture"),SELECT_PICTURE);
		
		
		break;
		case R.id.options_cancel:
			finish();
			break;

		default:
			break;
		}
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {  Uri selectedImageUri = null;
    String filePath = null;
    switch (requestCode) {
    case SELECT_PICTURE:
        if (resultCode == Activity.RESULT_OK) {
            selectedImageUri = data.getData();
            filePath = getPath(selectedImageUri);
            if (filePath != null) {
                decodeFile(filePath);
                p = yeshelp.getRoundedShape(bitmap,w,h);
                finish();
            } else {
                bitmap = null;
            }
        }
        //    new UplaodProfilePic().execute();
        break;

    case PICK_Camera_IMAGE:
        if (resultCode == RESULT_OK) {
            // use imageUri here to access the image

            selectedImageUri = imageUri;
        } else if (resultCode == RESULT_CANCELED) {
            Toast toast1 = Toast.makeText(options.this,"Picture was not taken.", Toast.LENGTH_LONG);
            toast1.setGravity(Gravity.CENTER, 0, 0);
            toast1.show();
//            Toast.makeText(this, "Picture was not taken",
//                    Toast.LENGTH_SHORT).show();
        } else {
            Toast toast1 = Toast.makeText(options.this,"Picture was not taken.", Toast.LENGTH_LONG);
            toast1.setGravity(Gravity.CENTER, 0, 0);
            toast1.show();
//            Toast.makeText(this, "Picture was not taken",
//                    Toast.LENGTH_SHORT).show();
        }
        if (selectedImageUri != null) {
            try {
                String filemanagerstring = selectedImageUri.getPath();

                String selectedImagePath = getPath(selectedImageUri);

                if (selectedImagePath != null) {
                    filePath = selectedImagePath;
                } else if (filemanagerstring != null) {
                    filePath = filemanagerstring;
                } else {
                    Toast toast1 = Toast.makeText(options.this,"Unknown path.", Toast.LENGTH_LONG);
                    toast1.setGravity(Gravity.CENTER, 0, 0);
                    toast1.show();
//                    Toast.makeText(getApplicationContext(), "Unknown path",
//                            Toast.LENGTH_LONG).show();

                }
                if (filePath != null) {

                    decodeFile(filePath);
                   p = yeshelp.getRoundedShape(bitmap,w,h);
                   finish();
                } else {
                    bitmap = null;
                }
            } catch (Exception e) {
                Toast toast1 = Toast.makeText(options.this,"Internal error.", Toast.LENGTH_LONG);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();
//                Toast.makeText(getApplicationContext(), "Internal error",
//                        Toast.LENGTH_LONG).show();

            }

        }



        break;
    }
    }
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
	}