package com.khizer.myproject;
 
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
 
public class Rating extends Activity {
 
	
	int flag= 0;
   // EditText txtName;
    //EditText txtPrice;
    //EditText txtDesc;
    //EditText txtCreatedAt;
    //Button btnSave;
    //Button btnDelete;
 
    //String pid;
 
    // Progress Dialog
	
	
    private ProgressDialog pDialog;
    
    RatingBar rb;
    Button b;
 
    // JSON parser class
    JSONParser jsonParser = new JSONParser();
 
       // url to update product
    //private static final String url_product_detials = "http://192.168.1.4/webguide/getrating.php";
    
    private static final String url_update_product = "http://projectwebguide.net63.net/rateit.php";
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_SITE = "site_name";
    private static final String TAG_RATE = "rating";
    /*private static final String TAG_NAME = "name";
    private static final String TAG_PRICE = "price";
    private static final String TAG_DESCRIPTION = "description";*/
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rateit);
 
        rb = (RatingBar) findViewById(R.id.ratingBar1);
        b = (Button) findViewById(R.id.submit);
        // save button
      /*btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);*/
 
      /*  // getting product details from intent
        Intent i = getIntent();
 
        // getting product id (pid) from intent
        pid = i.getStringExtra(TAG_PID);
 
        // Getting complete product details in background thread
        new GetProductDetails().execute();*/
 
        // save button click event
        b.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View arg0) {
            	flag=1;
                // starting background task to update product
                new SaveRatingDetails().execute();
            }
        });
 
    }
    @Override
    protected void onStop() {
    	SharedPreferences settings = getSharedPreferences("MYPREFS", 0);
    	SharedPreferences.Editor editor= settings.edit();
    	editor.putInt("fvalue",flag);
    	editor.commit();
    	super.onStop();
    }
    
    
 
    /**
     * Background Async Task to  Save product Details
     * */
    class SaveRatingDetails extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Rating.this);
            pDialog.setMessage("Saving the Rating ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
 
        /**
         * Saving product
         * */
        protected String doInBackground(String... args) {
 
            // getting updated data from EditTexts
            String rate = Float.toString(rb.getRating());
            String site = getIntent().getExtras().getString("name");
 
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(TAG_SITE, site));
            params.add(new BasicNameValuePair(TAG_RATE, rate));
           /* params.add(new BasicNameValuePair(TAG_PRICE, price));
            params.add(new BasicNameValuePair(TAG_DESCRIPTION, description));*/
 
            // sending modified data through http request
            // Notice that update product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_update_product,
                    "POST", params);

            // check json success tag
            try {
                int success = json.getInt(TAG_SUCCESS);
 
                if (success == 1) {
                    // successfully updated
                    Intent i = getIntent();
                    // send result code 100 to notify about product update
                    setResult(100, i);
                    finish();
                } else {
                    // failed to update product
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
 
            return null;
        }
 
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product uupdated
            pDialog.dismiss();
        }
    }
 
}