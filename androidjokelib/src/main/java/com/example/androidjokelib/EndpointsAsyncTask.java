package com.example.androidjokelib;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.nkt1001.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by nkt01 on 16.10.2016.
 */
public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {

    private JokeCallback callback;
    private String address;

    public final static String ADDRESS_DEFAULT = "10.0.2.2:8080";

    private static MyApi myApiService = null;

    private CountDownLatch doneSignal;

    EndpointsAsyncTask(@NonNull String address, JokeCallback callback) {
        this.address = address;
        this.callback = callback;
    }

    public EndpointsAsyncTask(CountDownLatch doneSignal) {
//        address = "192.168.88.249:8080";
        address = ADDRESS_DEFAULT;
        callback = null;
        this.doneSignal = doneSignal;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (callback != null)
            callback.jokePreparing();
    }

    @Override
    protected String doInBackground(Void... params) {

        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://" + address + "/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
//             end options for devappserver

            myApiService = builder.build();
        }

        try {
//            throw new IOException();
            String url = myApiService.obtainJoke().buildHttpRequestUrl().buildRelativeUrl();
            Log.d("TestCase", "doInBackground: " + url);
            return myApiService.obtainJoke().execute().getJoke();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (callback != null) {
            if (result.contains("connect timed out") || result.contains("failed to connect") || result.contains("socket failed")) callback.jokeFailed(result);
            else callback.jokeReady(result);
        } else if (doneSignal != null){
            doneSignal.countDown();
        }
    }

    interface JokeCallback {
        void jokePreparing();
        void jokeReady(String joke);
        void jokeFailed(String reason);
    }
}
