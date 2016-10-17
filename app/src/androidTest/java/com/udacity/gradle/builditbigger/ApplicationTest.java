package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.androidjokelib.EndpointsAsyncTask;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@RunWith(AndroidJUnit4.class)
public class ApplicationTest {

    @Test
    public void checkAsyncTask() throws InterruptedException, ExecutionException {
        Log.d("TestCase", "checkAsyncTask: " );

        CountDownLatch doneSignal = new CountDownLatch(1);

        EndpointsAsyncTask asyncTask = new EndpointsAsyncTask(doneSignal);
        asyncTask.execute();

        doneSignal.await();

        String joke = asyncTask.get();
        Log.d("TestCase", "checkAsyncTask: " + joke);
        assertFalse("Joke string is empty", joke.isEmpty());
        assertNotNull(joke);
    }
}