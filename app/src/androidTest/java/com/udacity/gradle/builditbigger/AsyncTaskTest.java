package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Nkt1001 on 18.10.2016.
 */

@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest {

    @Test
    public void checkEndpointsAsyncTask() throws InterruptedException, ExecutionException {
        Log.d("TestCase", "checkAsyncTask: " );
        CountDownLatch doneSignal = new CountDownLatch(1);

        EndpointsAsyncTask asyncTask = new EndpointsAsyncTask(doneSignal);
        asyncTask.execute();

        doneSignal.await();

        String joke = asyncTask.get().second;
        Log.d("TestCase", "checkAsyncTask: " + joke);
        assertFalse("Joke string is empty", joke.isEmpty());
        assertNotNull(joke);
    }
}
