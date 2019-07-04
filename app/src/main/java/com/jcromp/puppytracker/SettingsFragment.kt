package com.jcromp.puppytracker

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import java.util.*


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_settings_main, rootKey)

        findPreference<Preference>(getString(R.string.key_birthday)).setOnPreferenceClickListener {
            setPuppyBirthday()
            true
        }

    }

    private fun setPuppyBirthday() {
        println("Setting Puppy Birthday")
        //Get the stored date from shared preferences
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        var date = sharedPreferences.getLong(getString(R.string.key_birthday), 0)

        //Convert long to date
        var cal = Calendar.getInstance()
        if (date == 0L){
            date = System.currentTimeMillis()
        }
        cal.timeInMillis = date

        //Show Datepicker
        val newFragment = DatePickerDialog(activity, DatePickerDialog.OnDateSetListener{ view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            //Get the long and store it
            with(sharedPreferences.edit()){
                putLong(getString(R.string.key_birthday), cal.timeInMillis)
                commit()
            }

        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
    }


}
