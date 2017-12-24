package com.android.quakeInfo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yarolegovich.lovelydialog.LovelyInfoDialog;

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

        /*This shows up the alert box*/
        new LovelyInfoDialog(this)
                .setTopColorRes(R.color.colorPrimary)
                .setIcon(R.drawable.ic_info_outline_black_24dp)
                .setNotShowAgainOptionEnabled(0)
                .setNotShowAgainOptionChecked(true)
                .setTitle(R.string.info_title)
                .setMessage(R.string.info_message)
                .show();
    }

}


