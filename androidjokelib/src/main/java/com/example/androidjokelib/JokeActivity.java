package com.example.androidjokelib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class JokeActivity extends AppCompatActivity implements EndpointsAsyncTask.JokeCallback, JokeFragment.OnFragmentInteractionListener{

    private static final String TAG = "JokeActivity";
    private static final String KEY_LAST_JOKE = "KEY_LAST_JOKE";

    private Fragment progressFragment;
    private String mLastJoke;

    public static void startJokeActivity(Activity activity) {
        Intent intent = new Intent(activity, JokeActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        progressFragment = new ProgressFragment();

        if (savedInstanceState == null) loadJoke();
        else showJokeFragment(savedInstanceState.getString(KEY_LAST_JOKE));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_LAST_JOKE, mLastJoke);
        super.onSaveInstanceState(outState);
    }

    private void loadJoke() {
        new EndpointsAsyncTask("192.168.88.249:8080", this).execute();
    }


    @Override
    public void jokePreparing() {
        showProgressFragment();
    }

    @Override
    public void jokeReady(String joke) {
        showJokeFragment(joke);
    }

    @Override
    public void jokeFailed(String reason) {
        Toast.makeText(this, getString(R.string.error) + " (" + reason + ")", Toast.LENGTH_SHORT).show();
        String joke = getPreferences(MODE_PRIVATE).getString(KEY_LAST_JOKE, null);

        if (joke == null)
            finish();
        else showJokeFragment(joke);
    }

    private void showJokeFragment(String joke) {
        mLastJoke = joke;

        getPreferences(MODE_PRIVATE).edit().putString(KEY_LAST_JOKE, mLastJoke).apply();
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_joke, JokeFragment.newInstance(joke)).commit();
    }


    private void showProgressFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_joke, progressFragment).commit();
    }

    @Override
    public void onFragmentInteraction() {
        loadJoke();
    }
}
