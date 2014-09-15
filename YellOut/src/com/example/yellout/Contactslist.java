package com.example.yellout;

import java.util.ArrayList;

import android.R.array;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.PhoneLookup;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Contactslist extends Activity {
	String aNameFromContacts[];
	String aNumberFromContacts[];
	private ContextWrapper context;
	array_adapter adp;
	Context ctx;
	int w,h;
	ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contactslist);
		ctx=Contactslist.this;
		lv=(ListView)findViewById(R.id.listView1);
		 Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
		    String[] projection    = new String[] {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
		                         ContactsContract.CommonDataKinds.Phone.NUMBER}; 
		     Cursor people = getContentResolver().query(uri, projection, null, null, Phone.DISPLAY_NAME + " ASC");

		     int indexName = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
		     int indexNumber = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
		     ArrayList<contacts_model> arr_conm=new ArrayList<contacts_model>();
		     people.moveToFirst();
		     do {
		    	 contacts_model con_m=new contacts_model();
		         String name   = people.getString(indexName);
		         String number = people.getString(indexNumber);
		        
		      con_m.setName(name);
		      con_m.setNumber(number);
		      
		        // ArrayAdapter adapter=new ArrayAdapter<T>(getApplicationContext(), R.layout.activity_contactslist);
		       
		        arr_conm.add(con_m);
		        adp=new array_adapter(ctx,arr_conm,w,h);
		       //  Log.d("value", name);
		 		//Log.d("number", number);
		        
		     }
		    
		     while (people.moveToNext());
		     lv.setAdapter(adp);
		    
		     

		
		
		
		/*Cursor managedCursor = getContentResolver()
			    .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
			     new String[] {Phone._ID, Phone.DISPLAY_NAME, Phone.NUMBER}, null,null,  Phone.DISPLAY_NAME + " ASC");
		aNameFromContacts= new String[managedCursor.getCount()];  
		aNumberFromContacts= new String[managedCursor.getCount()];  
		int i = 0;

		int nameFieldColumnIndex = managedCursor.getColumnIndex(PhoneLookup.DISPLAY_NAME);
		int numberFieldColumnIndex = managedCursor.getColumnIndex(PhoneLookup.NORMALIZED_NUMBER);

		while(managedCursor.moveToNext()) {

		    String contactName = managedCursor.getString(nameFieldColumnIndex);
		    aNameFromContacts[i] =    contactName ; 

		  String number = managedCursor.getString(numberFieldColumnIndex);
		    aNumberFromContacts[i] =    number ;
		i++;
		Log.d("value", contactName);
		Log.d("number", number);
		}

		managedCursor.close();*/
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contactslist, menu);
		return true;
	}

}
