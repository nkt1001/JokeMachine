package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.androidjokelib.JokeActivity;


public class MainActivity extends AppCompatActivity implements EndpointsAsyncTask.JokeCallback {

    private static final String KEY_LAST_JOKE = "KEY_LAST_JOKE";

    private ProgressBar mProgress;
    private EndpointsAsyncTask mEndpoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgress = (ProgressBar) findViewById(R.id.pb_load);
    }

    @Override
    protected void onResume() {
        super.onResume();
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
        if (mEndpoint == null) mEndpoint = new EndpointsAsyncTask(this);

        if (mEndpoint.getStatus() != AsyncTask.Status.RUNNING) {
            mEndpoint = new EndpointsAsyncTask(this);
            mEndpoint.execute();
        }
    }

    private void showJoke(String joke) {
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString(KEY_LAST_JOKE, joke).apply();
        JokeActivity.startJokeActivity(this, joke);
    }

    @Override
    public void jokePreparing() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void jokeReady(Pair<Boolean, String> result) {
        mProgress.setVisibility(View.GONE);
        if (result.first) showJoke(result.second);
        else {
            Toast.makeText(MainActivity.this,
                    getApplicationContext().getString(com.example.androidjokelib.R.string.error) + " (" + result.second + ")",
                    Toast.LENGTH_SHORT).show();

            String joke = PreferenceManager.getDefaultSharedPreferences(this).getString(KEY_LAST_JOKE, null);

            if (joke != null) showJoke(joke);
        }
    }
}
