package com.rglama.lifecycletutorail;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.hardware.*;
import android.location.LocationManager;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private static final String STATE_SCORE = "";
    TextView mtextview;


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtextview= (TextView) findViewById(R.id.textview1);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // For the main activity, make sure the app icon in the action bar
            // does not behave as a button
            ActionBar actionBar = getActionBar();
            assert actionBar != null;
            actionBar.setHomeButtonEnabled(false);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    android.os.Debug.stopMethodTracing();

    }

    @Override
    protected void onPause() {
        super.onPause();

      //  if(mCamera!=null) mCamera.release();

    }

    @Override
    protected void onResume() {
        super.onResume();
   //     if(mCamera==null){
    //    initializeCamera();

     //   }
    }


    @Override
    protected void onStart() {
        super.onStart();  // Always call the superclass method first

        // The activity is either being restarted or started for the first time
        // so this is where we should make sure that GPS is enabled
        LocationManager locationManager =
                (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!gpsEnabled) {
            // Create a dialog here that requests the user to enable GPS, and use an intent
            // with the android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS action
            // to take the user to the Settings screen to enable GPS when they click "OK"
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();  // Always call the superclass method first

        // Activity being restarted from stopped state
    }


    @Override
    protected void onStop() {
        super.onStop();  // Always call the superclass method first

        // Save the note's current draft, because the activity is stopping
        // and we want to be sure the current note progress isn't lost.
        ContentValues values = new ContentValues();
        values.put(NotePad.Notes.COLUMN_NAME_NOTE, getCurrentNoteText());
        values.put(NotePad.Notes.COLUMN_NAME_TITLE, getCurrentNoteTitle());

        getContentResolver().update(
                mUri,    // The URI for the note to update.
                values,  // The map of column names and new values to apply to them.
                null,    // No SELECT criteria are used.
                null     // No WHERE columns are used.
        );
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putInt(STATE_SCORE, mCurrentScore);
        savedInstanceState.putInt(STATE_LEVEL, mCurrentLevel);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        int mCurrentScore = savedInstanceState.getInt(STATE_SCORE);
        int mCurrentLevel = savedInstanceState.getInt(STATE_LEVEL);
    }






}
