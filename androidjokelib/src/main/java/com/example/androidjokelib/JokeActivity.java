package com.example.androidjokelib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

public class JokeActivity extends AppCompatActivity {

    private static final String TAG = "JokeActivity";

    public static void startJokeActivity(Activity activity, String joke) {
        Intent intent = new Intent(activity, JokeActivity.class);
        intent.putExtra(TAG, joke);
        ActivityCompat.startActivity(activity, intent, null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
    }

    @Override
    protected void onStart() {
        super.onStart();

        String receivedJoke = getIntent().getStringExtra(TAG);
        showJokeFragment(receivedJoke);
    }

    private void showJokeFragment(String joke) {
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_joke, JokeFragment.newInstance(joke)).commit();
    }

}
