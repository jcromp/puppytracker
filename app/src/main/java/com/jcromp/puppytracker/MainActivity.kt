package com.jcromp.puppytracker

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.Calendar

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var viewModel: MainViewModel
    val dateFormat = SimpleDateFormat("E hh:mm a")
    var mTorchOn = false
    lateinit var puppyBirthday: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setPuppyBirthday()

        //setSupportActionBar(findViewById(R.id.toolbar))

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java);
        btnCrate.setOnClickListener(this)
        btnFood.setOnClickListener(this)
        btnPee.setOnClickListener(this)
        btnPoo.setOnClickListener(this)
        btnFlashlight.setOnClickListener(this)

        //Hide the flashlight button if the device doesnt have a flash
        if(!baseContext.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
            btnFlashlight.visibility = View.GONE
        }

        viewModel.getMostRecentCrateTime().observe(this, Observer { crate ->
            if(crate != null) {
                txtCrate?.text = dateFormat.format(crate.time)
            }
        })
        viewModel.getMostRecentFoodTime().observe(this, Observer { food ->
            if(food != null){
                txtFood?.text = dateFormat.format(food.time)
            }
        })
        viewModel.getMostRecentPeeTime().observe(this, Observer { pee ->
            if(pee != null){
                txtPee?.text = dateFormat.format(pee.time)
            }
        })
        viewModel.getMostRecentPooTime().observe(this, Observer { poo ->
            if(poo != null){
                txtPoo?.text = dateFormat.format(poo.time)
            }
        })
    }

    private fun setPuppyBirthday() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        var dateMilliseconds = sharedPreferences.getLong(getString(R.string.key_birthday), 0)
        if(dateMilliseconds == 0.toLong()){
            //Prompt the user to enter the puppies birthday
            println("PROMPT FOR BIRTHDAY")
        }

        //17th Jan
        var birthday = Calendar.getInstance()
        birthday.timeInMillis = dateMilliseconds

        //Get weeks difference between now and birthday
        var now = Calendar.getInstance()
        var weeksDiff = now.get(Calendar.WEEK_OF_YEAR) - birthday.get(Calendar.WEEK_OF_YEAR)
        txtAge?.text = getString(R.string.puppyage, weeksDiff)

        //Max hours in crate is months old + 1
        var monthsDiff = now.get(Calendar.MONTH) - birthday.get(Calendar.MONTH)
        txtRecCrate?.text = getString(R.string.puppymaxcrate, monthsDiff + 1)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnCrate -> recordCrateTime()
            R.id.btnFood -> recordFoodTime()
            R.id.btnPee -> recordPeeTime()
            R.id.btnPoo -> recordPooTime()
            R.id.btnFlashlight -> toggleFlashlight()

            else -> {
                println("$v not implemented")
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.menu_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    private fun toggleFlashlight() {
        val cameraManager = getSystemService (Context.CAMERA_SERVICE) as CameraManager
        var cameraId = ""
        //Find the back facing camera
        for(id in cameraManager.cameraIdList){
            if(cameraManager.getCameraCharacteristics(id).get(CameraCharacteristics.LENS_FACING) == CameraCharacteristics.LENS_FACING_BACK){
                cameraId = id
                break
            }
        }

        //If we found it toggle the torch
        if(!cameraId.isNullOrEmpty()){
            val characteristics = cameraManager.getCameraCharacteristics(cameraId)
            if(characteristics[CameraCharacteristics.FLASH_INFO_AVAILABLE]){
                cameraManager.setTorchMode(cameraId, !mTorchOn)
                mTorchOn = !mTorchOn
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState?.run {
            putBoolean("TORCHON", mTorchOn)
        }
        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState)
    }

    private fun recordFoodTime() {
        viewModel.addFood()
    }

    private fun recordPeeTime() {
        viewModel.addPee()
    }

    private fun recordPooTime() {
        viewModel.addPoo()
    }

    private fun printCrate() {
        println(viewModel.getAllCrates())
    }

    private fun recordCrateTime() {
        viewModel.addCrate()
    }
}