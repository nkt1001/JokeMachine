package com.example.androidjokelib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.JokeClass;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class JokeActivity extends AppCompatActivity {

    private static final String TAG = "JokeActivity";

    private String mCurrentJoke;

    public static void startJokeActivity(Activity activity, JokeClass joke) {
        Intent intent = new Intent(activity, JokeActivity.class);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(TAG, joke.getJoke().getJoke());
        ActivityCompat.startActivity(activity, intent, null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        mCurrentJoke = getIntent().getStringExtra(TAG);
        TextView tvJoke = (TextView) findViewById(R.id.tv_joke);
        tvJoke.setText(mCurrentJoke);
    }
}
