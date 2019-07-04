package com.jcromp.puppytracker

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() , View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setTitle(R.string.settings)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings_container, SettingsFragment())
            .commit()


    }

    override fun onClick(v: View?){
        when(v?.id){
            else -> {
                println("$v not implemented")
            }
        }
    }
}
