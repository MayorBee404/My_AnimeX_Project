package com.dicoding.myanimex.setting

import android.content.Context
import android.content.Intent
import android.media.audiofx.Equalizer
import android.os.Bundle
import android.provider.Settings
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.dicoding.core.ui.AnimeAdapter
import com.dicoding.myanimex.MainActivity
import com.dicoding.myanimex.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        if (activity != null) {
            (activity as MainActivity).supportActionBar?.title = getString(R.string.menu_setting)
        }

    }

    override fun onPreferenceTreeClick(preference: Preference): Boolean {

        preference.key  = "language"
        startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        return super.onPreferenceTreeClick(preference)
    }

}