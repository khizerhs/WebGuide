package com.khizer.myproject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Info extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);
		
		Button b=(Button) findViewById(R.id.rateit);
		TextView tv=(TextView) findViewById(R.id.ot_info_head);
		final String sn=getIntent().getExtras().getString("name");
		tv.setText(sn);
		
		if(sn.equals("w3schools"))
		{
			TextView tv1=(TextView) findViewById(R.id.link);
			tv1.setClickable(true);
			tv1.setMovementMethod(LinkMovementMethod.getInstance());
			String text = "<a href='http://www.w3schools.com'>http://www.w3schools.com</a>";
			tv1.setText(Html.fromHtml(text));
			
		}
		
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i= new Intent(Info.this,Rating.class);
				i.putExtra("name",sn);
				startActivity(i);
				
			}
		});
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		SharedPreferences settings = getSharedPreferences("MYPREFS", 0);
		int flag= settings.getInt("fvalue",0);
		if(flag==1){
		ProgressDialog pd;
		pd=new ProgressDialog(this);
		pd.setMessage("Updating Ratings...The Screen wil go blank but dont worry...we will be back in a moment");
		pd.setIndeterminate(true);
		pd.setCancelable(true);
		pd.show();
		}
		else
		{
			SharedPreferences.Editor editor= settings.edit();
			editor.putInt("fvalue", 0);
			editor.commit();
		}
		
	}
}
