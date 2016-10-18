package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Pair;

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
class EndpointsAsyncTask extends AsyncTask<Void, Void, Pair<Boolean, String>> {

    private JokeCallback callback;

    private final static String ROOT_URL = "https://gcebackend.appspot.com/_ah/api/";

    private static MyApi myApiService = null;

    private CountDownLatch doneSignal;

    EndpointsAsyncTask(JokeCallback callback) {

        this.callback = callback;
    }

    //testing
    EndpointsAsyncTask(CountDownLatch doneSignal) {
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
    protected Pair<Boolean, String> doInBackground(Void... params) {

        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl(ROOT_URL)
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
            return new Pair<>(true, myApiService.obtainJoke().execute().getJoke());
        } catch (IOException e) {
            return new Pair<>(false, e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(Pair<Boolean, String> result) {
        if (callback != null) {
            callback.jokeReady(result);
        } else if (doneSignal != null){
            doneSignal.countDown();
        }
    }

    interface JokeCallback {
        void jokePreparing();
        void jokeReady(Pair<Boolean, String> result);
    }
}
