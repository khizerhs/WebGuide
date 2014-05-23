package com.khizer.myproject;


import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Search extends Activity {

	private EditText et1;
	private EditText et2;

    /** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

       et1 = (EditText) findViewById(R.id.editText1);
       et2 = (EditText) findViewById(R.id.editText2);
       
       Button b = (Button) findViewById(R.id.rateit);
   b.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		try {
   		 Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            String term1 = et1.getText().toString();
            String term2 = et2.getText().toString();
            String term;
            if( term2.isEmpty()){
            term = term1;
            }
            else{
            	term = term1+" site:"+term2;
            }
            intent.putExtra(SearchManager.QUERY, term);
            startActivity(intent);
		} catch (Exception e) {
			Log.d("search", "error in search ");
		}
		
	}
});
    	
    }
}