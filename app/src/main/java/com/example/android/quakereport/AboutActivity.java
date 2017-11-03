package com.example.android.quakereport;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.eggheadgames.aboutbox.AboutConfig;
import com.eggheadgames.aboutbox.IAnalytic;
import com.eggheadgames.aboutbox.IDialog;

/**
 * Created by kvaib on 03-11-2017.
 */

public class AboutActivity extends AppCompatActivity {

    private static final String TWITTER_USER_NAME = "@vaibhav_khulbe";
    private static final String WEB_HOME_PAGE = "https://about.me/vaibhav_khulbe";
    private static final String APP_PUBLISHER = "https://play.google.com/store/apps/developer?id=Vaibhav%20Khulbe&hl=en";
    private static final String EMAIL_ADDRESS = "khulbevaibhavdev@gmail.com";
    private static final String EMAIL_SUBJECT = "Quake Report app acknowledgements and/or issues";
    private static final String EMAIL_BODY = "Please explain your experience with this app here...This may include bugs" +
            "or issues you may be facing or what you liked about the app along with improvements";
    private static final String COMPANY_HTML_PATH = "https://github.com/Kvaibhav01/Quake-Report";
    private static final String PRIVACY_HTML_PATH= "https://github.com/Kvaibhav01/Quake-Report/blob/master/LICENSE";
    private static final String ACKNOWLEDGMENT_HTML_PATH = "https://github.com/eggheadgames/android-about-box" ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Create About activity */
        AboutConfig aboutConfig = AboutConfig.getInstance();
        aboutConfig.appName = getString(R.string.app_name);
        aboutConfig.appIcon = R.mipmap.ic_launcher;
        aboutConfig.version = "1.0.0";
        aboutConfig.author = "Vaibhav Khulbe";
        aboutConfig.aboutLabelTitle = "About";
        aboutConfig.packageName = getApplicationContext().getPackageName();


        aboutConfig.twitterUserName = TWITTER_USER_NAME;
        aboutConfig.webHomePage = WEB_HOME_PAGE;

        // app publisher for "Try Other Apps" item
        aboutConfig.appPublisher = APP_PUBLISHER;

        aboutConfig.companyHtmlPath = COMPANY_HTML_PATH;
        aboutConfig.privacyHtmlPath = PRIVACY_HTML_PATH;
        aboutConfig.acknowledgmentHtmlPath = ACKNOWLEDGMENT_HTML_PATH;

        aboutConfig.dialog = new IDialog() {
            @Override
            public void open(AppCompatActivity appCompatActivity, String url, String tag) {
                // handle custom implementations of WebView. It will be called when user click to web items. (Example: "Privacy", "Acknowledgments" and "About")
            }
        };

        aboutConfig.analytics = new IAnalytic() {
            @Override
            public void logUiEvent(String s, String s1) {
                // handle log events.
            }

            @Override
            public void logException(Exception e, boolean b) {
                // handle exception events.
            }
        };
        // set it only if aboutConfig.analytics is defined.
        aboutConfig.logUiEventName = "Log";

        // Contact Support email details
        aboutConfig.emailAddress = EMAIL_ADDRESS;
        aboutConfig.emailSubject = EMAIL_SUBJECT;
        aboutConfig.emailBody = EMAIL_BODY;


        aboutConfig.shareMessage = getString(R.string.share_message);
        aboutConfig.sharingTitle = getString(R.string.sharing_title);

    }
}
