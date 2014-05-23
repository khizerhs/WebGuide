package com.khizer.myproject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


public class online_tut extends ListActivity {
	 
	ProgressDialog pDialog;

	
	final JSONParser jsonParser = new JSONParser();
	final String url_get_rating = "http://projectwebguide.net63.net/getrating.php";
   	final String TAG_SUCCESS = "success";
    final String TAG_RATE = "rating";
	String TAG_SITE="site_name";
	String TAG_PRODUCT = "product";
	
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.online_tut);
    setListAdapter(new MyAdapter
   			(this, android.R.layout.simple_list_item_1,R.id.ot_custlist_textview1,
   					getResources().getStringArray(R.array.online_tutorials_menu)));
    
	
	}
	

	
	
	@Override
	protected void onRestart() {
		super.onRestart();
		SharedPreferences settings = getSharedPreferences("MYPREFS",0);
		int flag= settings.getInt("fvalue", 0);
		if(flag==1)
		{
		new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            	finish();
        		Intent intent=getIntent();
        		startActivity(intent);    	
            }
        }, 1500);
		}
		
	} 
	
	private class MyAdapter extends ArrayAdapter<String>
	{
		
		public MyAdapter(Context context,int resource,
				int textViewResourceId,String[] strings){
					
			super(context,resource,textViewResourceId,strings);
			
		}
		
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			LayoutInflater infalter= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
			View row=infalter.inflate(R.layout.online_tut_menu, parent, false);
			final String[] items= getResources().getStringArray(R.array.online_tutorials_menu);

			RatingBar rbs=(RatingBar) row.findViewById(R.id.ratingbar_Small);
			ImageView iv= (ImageView) row.findViewById(R.id.imageView1);
			TextView tv=(TextView) row.findViewById(R.id.ot_info_head);
			ImageView iv1=(ImageView) row.findViewById(R.id.image_info);
			TextView tv1= (TextView) row.findViewById(R.id.rating_text);
			tv.setText(items[position]);
			
			
			iv1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent in=new Intent(online_tut.this,Info.class);
					if(items[position].equals("Lynda"))
					{
						in.putExtra("name","Lynda");
					}
					else if(items[position].equals("My Bring Back"))
					{
						in.putExtra("name","My Bring Back");
					}else if(items[position].equals("New Boston"))
					{
						in.putExtra("name","New Boston");
					}else if(items[position].equals("NPTEL"))
					{
						in.putExtra("name","NPTEL");
					}
					else if(items[position].equals("w3schools"))
					{
						in.putExtra("name","w3schools");
					}else if(items[position].equals("Udacity"))
					{
						in.putExtra("name","Udacity");
					}else if(items[position].equals("Coursera"))
					{
						in.putExtra("name","Coursera");
					}
					startActivity(in);
					
				}
			});
			
			if(items[position].equals("Lynda"))
			{
				try {
					
					String f=new GetRating().execute("Lynda").get();
					tv1.setText(f);
					rbs.setRating(Float.parseFloat(f));
				} catch (InterruptedException e) {
					Log.d("khizer","IEX");
					e.printStackTrace();
				} catch (ExecutionException e) {
					Log.d("khzer","khzier");
					e.printStackTrace();
				}
				
				iv.setImageResource(R.drawable.lynda);
				
				}
			else if(items[position].equals("My Bring Back"))
			{
				
				try {
					String f=new GetRating().execute("My Bring Back").get();
					tv1.setText(f);
					rbs.setRating(Float.parseFloat(f));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				iv.setImageResource(R.drawable.mybringback);
				
					
				
			}
			else if(items[position].equals("New Boston"))
			{
				try {
					
					String f=new GetRating().execute("New Boston").get();
					tv1.setText(f);
					rbs.setRating(Float.parseFloat(f));
				} catch (InterruptedException e) {
					Log.d("khizer","IEX");
					e.printStackTrace();
				} catch (ExecutionException e) {
					Log.d("khzer","khzier");
					e.printStackTrace();
				}
				iv.setImageResource(R.drawable.newboston);
				
			}
			else if(items[position].equals("NPTEL"))
			{
				try {
					String f=new GetRating().execute("NPTEL").get();
					tv1.setText(f);
					rbs.setRating(Float.parseFloat(f));
				} catch (InterruptedException e) {
					Log.d("khizer","IEX");
					e.printStackTrace();
				} catch (ExecutionException e) {
					Log.d("khzer","khzier");
					e.printStackTrace();
				}
				iv.setImageResource(R.drawable.nptel);
				
			}
			else if(items[position].equals("w3schools"))
			{
				try {
					String f=new GetRating().execute("w3schools").get();
					tv1.setText(f);
					rbs.setRating(Float.parseFloat(f));
				} catch (InterruptedException e) {
					Log.d("khizer","IEX");
					e.printStackTrace();
				} catch (ExecutionException e) {
					Log.d("khzer","khzier");
					e.printStackTrace();
				}
				iv.setImageResource(R.drawable.w3schools);
				
			}
			else if(items[position].equals("Udacity"))
			{
				try {
					String f=new GetRating().execute("Udacity").get();
					tv1.setText(f);
					rbs.setRating(Float.parseFloat(f));
				} catch (InterruptedException e) {
					Log.d("khizer","IEX");
					e.printStackTrace();
				} catch (ExecutionException e) {
					Log.d("khzer","khzier");
					e.printStackTrace();
				}
				iv.setImageResource(R.drawable.udacity);
				
			}
			else if(items[position].equals("Coursera"))
			{
				try {
					String f=new GetRating().execute("Coursera").get();
					tv1.setText(f);
					rbs.setRating(Float.parseFloat(f));
				} catch (InterruptedException e) {
					Log.d("khizer","IEX");
					e.printStackTrace();
				} catch (ExecutionException e) {
					Log.d("khzer","khzier");
					e.printStackTrace();
				}
				iv.setImageResource(R.drawable.coursera);
				
			}
			return row;
		}
	}
	class GetRating extends AsyncTask<String,String,String>{
		
		//@Override
		   // protected void onPreExecute() {
	         //   super.onPreExecute();
	            
	        //}
		
		@Override
		protected String doInBackground(String... arg) {
			
			int success;
			String f=null;
			
		       try {         
		           	List<NameValuePair> params = new ArrayList<NameValuePair>();
		                params.add(new BasicNameValuePair("site_name",arg[0]));
		                JSONObject json = jsonParser.makeHttpRequest(
		                               url_get_rating, "GET", params);
		               // TAG_SITE=arg[0];
		                Log.d("Rating", json.toString());
		                success = json.getInt(TAG_SUCCESS);
		                if (success == 1) {
		                JSONArray productObj = json
		                                   .getJSONArray(TAG_PRODUCT); 
		                JSONObject product = productObj.getJSONObject(0);
		                
		                 f=product.getString(TAG_RATE);
		                
		                       }
		                else{
		                           Log.d("Rating","Site Not Found");
		                       }
		                   }catch (JSONException e) {
		                       e.printStackTrace();
		                   }
			return f;
		
		}
		
	/*	protected void onPostExecute(String file_url) {
	     pDialog.dismiss();
	     }*/

 }

	
}