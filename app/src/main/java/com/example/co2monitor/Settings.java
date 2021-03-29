package com.example.co2monitor;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceActivity;


public class Settings extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        addPreferencesFromResource(R.xml.settings);
    }
}
