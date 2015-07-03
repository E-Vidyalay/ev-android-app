package net.evidyalya.evidyalay;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 1046208 on 6/1/2015.
 */
public class NetworkUtil {

    String result = null;
    String line = null;
    int code = 0;
    boolean net = false;

    public boolean getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            try {
                net = new CheckInternet().execute().get();
                if (net)
                    return true;
                else
                    return false;
            } catch (Exception e) {}
        } else
            return false;
        return false;
    }

    private class CheckInternet extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            // TODO Auto-generated method stub
            try {
                URL url = new URL("http://learnlabs.in/check_internet.php?checkflag=1");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                result = sb.toString();
                JSONObject json_data = new JSONObject(result);
                code = (json_data.getInt("code"));
                if (code == 1) {
                    net = true;
                } else {
                    net =  false;
                }
            } catch (Exception e) {
            }
            return net;
        }
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }
}