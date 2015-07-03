package net.evidyalya.evidyalay;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MainActivity extends ActionBarActivity {
    private WebView wv_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wv_main=(WebView)findViewById(R.id.activity_main_webview);
        WebSettings webSettings = wv_main.getSettings();
        webSettings.setJavaScriptEnabled(true);

        /*loading the url into webview*/
        wv_main.loadUrl("http://ev.learnlabs.in");

        /*In order to access other links of the site*/
        wv_main.setWebViewClient(new WebViewClient());

    }
    /*To retain the back history of urls*/
    @Override
    public void onBackPressed() {
        if(wv_main.canGoBack()) {
            wv_main.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
