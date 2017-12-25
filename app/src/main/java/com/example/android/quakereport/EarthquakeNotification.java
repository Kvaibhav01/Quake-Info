package com.android.quakeInfo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by kvaib on 19-11-2017.
 */

public class EarthquakeNotification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_notification);

        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("https://earthquake.usgs.gov/ens/");
        myWebView.setWebViewClient(new WebViewClient());
    }

}


