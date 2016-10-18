package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class MainActivity extends AppCompatActivity {


    private EndpointsAsyncTask endpoint;

    private ProgressBar pbLoad;
    private InterstitialAd mInterstitialAd;
    private EndpointsAsyncTask.JokeCallback jokeCallback = new EndpointsAsyncTask.JokeCallback() {

        @Override
        public void jokePreparing() {
            pbLoad.setVisibility(View.VISIBLE);
        }

        @Override
        public void jokeReady(Pair<Boolean, String> result) {
            pbLoad.setVisibility(View.GONE);

            Toast toast = Toast.makeText(MainActivity.this, result.second, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pbLoad = (ProgressBar) findViewById(R.id.pb_load);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.banner_ad_unit_id));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                loadJoke();
            }
        });

        requestNewInterstitial();
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (endpoint != null && endpoint.getStatus() == AsyncTask.Status.RUNNING)
            endpoint.cancel(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {

        if (mInterstitialAd.isLoaded()) mInterstitialAd.show();
        else loadJoke();

    }

    private void loadJoke() {
        if (endpoint == null) endpoint = new EndpointsAsyncTask(jokeCallback);
        if (endpoint.getStatus() != AsyncTask.Status.RUNNING) {
            endpoint = new EndpointsAsyncTask(jokeCallback);
            endpoint.execute();
        }
    }

}
