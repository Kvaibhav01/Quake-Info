package com.theQuake.quakeInfo;



import android.support.v4.widget.SwipeRefreshLayout;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.eggheadgames.aboutbox.AboutConfig;
import com.eggheadgames.aboutbox.IAnalytic;
import com.eggheadgames.aboutbox.IDialog;
import com.eggheadgames.aboutbox.activity.AboutActivity;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class EarthquakeActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener, SwipeRefreshLayout.OnRefreshListener, LoaderManager.LoaderCallbacks<List<Earthquake>> {
    public static final String MyPrefs = "MyPrefs";

    /** URL for earthquake data from the USGS dataset */
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query";

    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int EARTHQUAKE_LOADER_ID = 1;

    /** Adapter for the list of earthquakes */
    private EarthquakeAdapter mAdapter;

    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;

    SwipeRefreshLayout swipe;

    private static final String LOG_TAG = EarthquakeActivity.class.getSimpleName();


    private ListView earthquakeListView;

    private static final String TWITTER_USER_NAME = "vaibhav_khulbe";
    private static final String WEB_HOME_PAGE = "https://about.me/vaibhav_khulbe";
    private static final String APP_PUBLISHER = "https://play.google.com/store/apps/developer?id=Vaibhav%20Khulbe&hl=en";
    private static final String EMAIL_ADDRESS = "khulbevaibhavdev@gmail.com";
    private static final String EMAIL_SUBJECT = "Quake Info app acknowledgements and/or issues";
    private static final String EMAIL_BODY = "Please explain your experience with this app here...This may include bugs" +
            " or issues you may be facing or what you liked about the app along with improvements. :) (MAKE SURE to clear out these lines before sending the mail to us)";

    private Drawer mDrawer;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);

        setUpDrawer();

        swipe = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swipe.setOnRefreshListener(this);
        swipe.setColorSchemeColors(getResources().getColor(R.color.colorAccent));

        /* Start the intro only once */
        SharedPreferences sp = getSharedPreferences(MyPrefs, Context.MODE_PRIVATE);
        if (!sp.getBoolean("first", false)) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("first", true);
            editor.apply();
            Intent intent = new Intent(this, IntroActivity.class);
            startActivity(intent);
        }

        //Call and launch About activity
        initAboutActivity();

        // Find a reference to the {@link ListView} in the layout
        earthquakeListView = (ListView) findViewById(R.id.list);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        earthquakeListView.setEmptyView(mEmptyStateTextView);

        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);

        // Obtain a reference to the SharedPreferences file for this app
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        // And register to be notified of preference changes
        // So we know when the user has adjusted the query settings
        prefs.registerOnSharedPreferenceChangeListener(this);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected earthquake.
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                Earthquake currentEarthquake = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });
        getSupportLoaderManager().initLoader(EARTHQUAKE_LOADER_ID, null, this);

    }



    /*Code to launch About activity */
    public void initAboutActivity()
    {
        /* Create About activity */
        AboutConfig aboutConfig = AboutConfig.getInstance();
        aboutConfig.appName = getString(R.string.app_name);
        aboutConfig.appIcon = R.mipmap.ic_launcher;
        aboutConfig.version = "1.0.0";
        aboutConfig.author = "Vaibhav Khulbe";
        aboutConfig.aboutLabelTitle = "About";
        aboutConfig.packageName = getApplicationContext().getPackageName();

        aboutConfig.appPublisher = APP_PUBLISHER;

        aboutConfig.twitterUserName = TWITTER_USER_NAME;
        aboutConfig.webHomePage = WEB_HOME_PAGE;


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

    @Override
    public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
        if (key.equals(getString(R.string.settings_min_magnitude_key)) ||
                key.equals(getString(R.string.settings_order_by_key))){
            // Clear the ListView as a new query will be kicked off
            mAdapter.clear();

            // Hide the empty state text view as the loading indicator will be displayed
            mEmptyStateTextView.setVisibility(View.GONE);

            // Show the loading indicator while new data is being fetched
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.VISIBLE);

            // Restart the loader to requery the USGS as the query settings have been updated
            getSupportLoaderManager().restartLoader(EARTHQUAKE_LOADER_ID, null, this);
        }
    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int i, Bundle bundle) {

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String minMagnitude = sharedPrefs.getString(
                getString(R.string.settings_min_magnitude_key),
                getString(R.string.settings_min_magnitude_default));

        String orderBy = sharedPrefs.getString(
                getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default)
        );

        String region = sharedPrefs.getString(
                getString(R.string.settings_narrow_by_region_key),
                getString(R.string.settings_narrow_by_region_default)
        );

        String radius = sharedPrefs.getString(
                getString(R.string.settings_maximum_radius_key),
                getString(R.string.settings_maximum_radius_default)
        );

        List<Country> countries = new ArrayList<>();

        try {
            countries = Utils.generateCountryList(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Double latitude = 0.0;
        Double longitude = 0.0;
        for (Country country : countries) {
            if(country.getName().equalsIgnoreCase(region)){
                latitude = country.getLatitude();
                longitude = country.getLongitude();
            }
        }

        Uri baseUri = Uri.parse(USGS_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("format", "geojson");
        uriBuilder.appendQueryParameter("limit", "100");
        uriBuilder.appendQueryParameter("minmag", minMagnitude);
        uriBuilder.appendQueryParameter("orderby", orderBy);

        if(latitude != 0.0 && longitude != 0.0){
            uriBuilder.appendQueryParameter("latitude", String.valueOf(latitude.intValue()));
            uriBuilder.appendQueryParameter("longitude", String.valueOf(longitude.intValue()));
            uriBuilder.appendQueryParameter("maxradius", radius);
        }

        String url = uriBuilder.toString();
        return new EarthquakeLoader(this, url);
    }




    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakes) {
        swipe.setRefreshing(false);

        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);



        if (earthquakes != null && !earthquakes.isEmpty()) {
            this.showResults(earthquakes);
        } else {
            this.hideResults();
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }

    /**
     * method to show results
     */
    private void showResults(List<Earthquake> earthquakeList) {
        mAdapter.clear();
        earthquakeListView.setVisibility(View.VISIBLE);
        mEmptyStateTextView.setVisibility(View.GONE);
        mAdapter.setNotifyOnChange(false);
        mAdapter.setNotifyOnChange(true);
        mAdapter.addAll(earthquakeList);
    }

    /**
     * method to hide results also checks internet connection
     */
    private void hideResults() {
        earthquakeListView.setVisibility(View.GONE);
        mEmptyStateTextView.setVisibility(View.VISIBLE);
        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            mEmptyStateTextView.setText(R.string.no_earthquakes);
            Log.e(LOG_TAG, "no earthquakes data");

        } else {
            mEmptyStateTextView.setText(R.string.no_internet_connection);
            Log.e(LOG_TAG, "no internet");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        if (id == R.id.action_about) {
            Intent actionIntent = new Intent(this, AboutActivity.class);
            startActivity(actionIntent);
            return true;
        }
        if (id == R.id.action_did_you_feel_it){

            Intent feelItIntent = new Intent(this, DidYouFeel.class);
            startActivity(feelItIntent);
            return true;
        }
        if (id == R.id.action_more_apps){

            Uri uri = Uri.parse( "https://play.google.com/store/apps/developer?id=Vaibhav+Khulbe" );
            startActivity( new Intent( Intent.ACTION_VIEW, uri ) );
        }
        if (id == R.id.fork_project){

            Uri uri = Uri.parse( "https://github.com/Kvaibhav01/Quake-Info" );
            startActivity( new Intent( Intent.ACTION_VIEW, uri ) );
        }

        if (id == R.id.notification){

            Intent notificationIntent = new Intent(this, EarthquakeNotification.class);
            startActivity(notificationIntent);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onRefresh() {
        getSupportLoaderManager().restartLoader(EARTHQUAKE_LOADER_ID, null, this);
        Toast.makeText(this, R.string.list_refreshed, Toast.LENGTH_SHORT).show();

    }

    private void setUpDrawer() {
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(R.id.action_did_you_feel_it).withName(R.string.did_you_feel_it);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(R.id.action_more_apps).withName(R.string.more_apps);
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(R.id.fork_project).withName(R.string.fork_on_github);
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(R.id.notification).withName(R.string.get_notification_alert);
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(R.id.action_about).withName(R.string.about);

        mDrawer = new DrawerBuilder().withActivity(this)
                .withTranslucentStatusBar(false)
                .withToolbar(toolbar)
                .addDrawerItems(item1, item2, item3, item4, item5)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        long id = drawerItem.getIdentifier();

                        if (id == R.id.action_about) {
                            Intent actionIntent = new Intent(EarthquakeActivity.this, AboutActivity.class);
                            startActivity(actionIntent);
                            mDrawer.closeDrawer();
                            return true;
                        }
                        if (id == R.id.action_did_you_feel_it) {
                            Intent feelItIntent = new Intent(EarthquakeActivity.this, DidYouFeel.class);
                            startActivity(feelItIntent);
                            mDrawer.closeDrawer();
                            return true;
                        }
                        if (id == R.id.action_more_apps) {
                            Uri uri = Uri.parse("https://play.google.com/store/apps/developer?id=Vaibhav+Khulbe");
                            startActivity(new Intent(Intent.ACTION_VIEW, uri));
                            mDrawer.closeDrawer();
                            return true;
                        }
                        if (id == R.id.fork_project) {
                            Uri uri = Uri.parse("https://github.com/Kvaibhav01/Quake-Info");
                            startActivity(new Intent(Intent.ACTION_VIEW, uri));
                            mDrawer.closeDrawer();
                            return true;
                        }
                        if (id == R.id.notification) {
                            Intent notificationIntent = new Intent(EarthquakeActivity.this, EarthquakeNotification.class);
                            startActivity(notificationIntent);
                            mDrawer.closeDrawer();
                            return true;
                        }
                        return false;
                    }
                })
                .build();
    }
}
