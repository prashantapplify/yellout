package com.example.yellout;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class array_adapter extends BaseAdapter {
	Context adap_con;
	LayoutInflater adap_layinf;
	ArrayList<contacts_model> adap_arrconm;
	int adap_h;
	int adap_w;

	public array_adapter(Context ctx,ArrayList<contacts_model> arr_conm,int w,int h) {
		// TODO Auto-generated constructor stub
		adap_layinf=LayoutInflater.from(ctx);
		adap_arrconm=arr_conm;
		adap_con=ctx;
		//adap_w=w;
		//adap_h=h;
		
	}
		
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return adap_arrconm.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return adap_arrconm.get(position);
		
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final viewholder holder;
		
		if(convertView==null)
		{
			
			convertView=adap_layinf.inflate(R.layout.array_contacts, null);
			holder=new viewholder();
			holder.adap_name=(TextView)convertView.findViewById(R.id.array_name);
			holder.adap_number=(TextView)convertView.findViewById(R.id.array_number);
			
			convertView.setTag(holder);
		}
		else
			
			holder=(viewholder)convertView.getTag();
			
			holder.adap_name.setText(adap_arrconm.get(position).getName());
			holder.adap_number.setText(adap_arrconm.get(position).getNumber());
			
			
		
		return convertView;
	}
	public static class viewholder
	{
		public TextView adap_name,adap_number;
	}
	
}

