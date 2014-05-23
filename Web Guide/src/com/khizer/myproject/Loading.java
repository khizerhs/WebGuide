package com.khizer.myproject;

import android.app.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class Loading extends Activity {
	private static int SPLASH_TIME_OUT = 3000;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
    	ProgressBar pb= (ProgressBar) findViewById(R.id.ld_progressBar);
        pb.setIndeterminate(true);
        new Handler().postDelayed(new Runnable() {
 
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
 
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activit
 
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
 
}
