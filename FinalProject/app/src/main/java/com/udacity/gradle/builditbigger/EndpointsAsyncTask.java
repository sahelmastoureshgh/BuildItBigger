package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.sahelmastoureshgh.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.sahelmastoureshgh.jokeviewer.JokeActivity;

import java.io.IOException;

/**
 * Created by sahelmastoureshgh on 12/13/15.
 */
public class EndpointsAsyncTask  extends AsyncTask<Context, Void, String> {
    private static MyApi myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Context... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://androidbackend-1159.appspot.com/_ah/api/");
            // end options for devappserver

            myApiService = builder.build();
        }
        if (params != null) {
            context = params[0];
        }


        try {
            //call api of sayJoke to get joke
            return myApiService.sayJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Send joke to view activity of joke to view joke in joke viewer
     * @param s
     */
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (context != null) {
            Intent myIntent = new Intent(context,JokeActivity.class);
            myIntent.putExtra(JokeActivity.JOKE_TEXT,s);
            context.startActivity(myIntent);

        }

    }
}
