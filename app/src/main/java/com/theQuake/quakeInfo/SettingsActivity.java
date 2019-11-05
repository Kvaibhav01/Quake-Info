package com.theQuake.quakeInfo;

/**
 * Created by kvaib on 17-11-2017.
 */

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;


public class SettingsActivity extends AppCompatActivity {

    //private EarthquakeActivity earthquakeActivity = new EarthquakeActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getCurrentTheme().equals("Off")) {
            setTheme(R.style.AppTheme);
        }
        else
            setTheme(R.style.AppThemeDarkActionBar);

        setContentView(R.layout.settings_activity);
    }

    public static class EarthquakePreferenceFragment extends PreferenceFragment
            implements Preference.OnPreferenceChangeListener {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_main);

            Preference minMagnitude = findPreference(getString(R.string.settings_min_magnitude_key));
            bindPreferenceSummaryToValue(minMagnitude);

            Preference orderBy = findPreference(getString(R.string.settings_order_by_key));
            bindPreferenceSummaryToValue(orderBy);

            Preference narrowByRegion = findPreference(getString(R.string.settings_narrow_by_region_key));
            bindPreferenceSummaryToValue(narrowByRegion);

            Preference maximumRadius = findPreference(getString(R.string.settings_maximum_radius_key));
            bindPreferenceSummaryToValue(maximumRadius);

            Preference darkTheme = findPreference(getString(R.string.settings_dark_theme));
            bindPreferenceSummaryToValue(darkTheme);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();
            if (preference instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) preference;
                int prefIndex = listPreference.findIndexOfValue(stringValue);
                if (prefIndex >= 0) {
                    CharSequence[] labels = listPreference.getEntries();
                    preference.setSummary(labels[prefIndex]);
                }
            } else {
                preference.setSummary(stringValue);
            }
            return true;
        }

        private void bindPreferenceSummaryToValue(Preference preference) {
            preference.setOnPreferenceChangeListener(this);
            SharedPreferences preferences =
                    PreferenceManager.getDefaultSharedPreferences(preference.getContext());
            String preferenceString = preferences.getString(preference.getKey(), "");
            onPreferenceChange(preference, preferenceString);
        }
    }

    public String getCurrentTheme() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPreferences.getString(
                getString(R.string.settings_dark_theme),
                getString(R.string.settings_dark_theme_off));
    }
}
