package com.cursosandroidant.profile

import android.os.Bundle
import androidx.core.content.edit
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreferenceCompat

class SettingsFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

        setPreferencesFromResource(R.xml.preferences, rootKey)

        val deleteUserDataPreference = findPreference<Preference>(
            getString(R.string.preferences_key_delete_data))

        deleteUserDataPreference?.setOnPreferenceClickListener {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            sharedPreferences.edit {
                putString(getString(R.string.key_image), null)
                putString(getString(R.string.key_name), null)
                putString(getString(R.string.key_email), null)
                putString(getString(R.string.key_website), null)
                putString(getString(R.string.key_phone), null)
                putString(getString(R.string.key_latitude), null)
                putString(getString(R.string.key_logitude), null)
                apply()
            }
            true
        }

        val switchPreferenceCompat = findPreference<SwitchPreferenceCompat>(
            getString(R.string.preferences_key_eneable_clicks))
        val listPreference = findPreference<ListPreference>(
            getString(R.string.preferences_key_iu_img_size))

        val restoreAllPreference = findPreference<Preference>(
            getString(R.string.preferences_key_restore_data))
        restoreAllPreference?.setOnPreferenceClickListener {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            sharedPreferences
                .edit()
                .clear()
                .apply()

            switchPreferenceCompat?.isChecked = true
            listPreference?.value = getString(R.string.preferences_img_key_size_large)
            true
        }

        val restoreSettings = findPreference<Preference>(
            getString(R.string.preferences_key_restore_settings))
        restoreSettings?.setOnPreferenceClickListener {

            switchPreferenceCompat?.isChecked = true
            listPreference?.value = getString(R.string.preferences_img_key_size_large)
            true
        }
    }
}