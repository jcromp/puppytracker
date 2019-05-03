package com.jcromp.puppytracker.db

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.jcromp.puppytracker.db.dao.CrateDao
import com.jcromp.puppytracker.db.dao.FoodDao
import com.jcromp.puppytracker.db.dao.PeeDao
import com.jcromp.puppytracker.db.dao.PooDao
import com.jcromp.puppytracker.db.entity.Crate
import com.jcromp.puppytracker.db.entity.Food
import com.jcromp.puppytracker.db.entity.Pee
import com.jcromp.puppytracker.db.entity.Poo

class EntityRepo(app: Application){
    val db: AppDatabase = AppDatabase.getInstance(app)
    private var mCrateDao: CrateDao
    private var mFoodDao: FoodDao
    private var mPeeDao: PeeDao
    private var mPooDao: PooDao

    init{
        mCrateDao = db.getCrateDao()
        mFoodDao = db.getFoodDao()
        mPeeDao = db.getPeeDao()
        mPooDao = db.getPooDao()
    }

    fun getAllCrates(): List<Crate>{
        return GetAllCrates(mCrateDao).execute().get()
    }

    fun addCrate(newCrate: Crate) {
        AddCrate(mCrateDao).execute(newCrate)
    }

    fun addFood(newFood: Food) {
        AddFood(mFoodDao).execute(newFood)
    }

    fun addPoo(newPoo: Poo) {
        AddPoo(mPooDao).execute(newPoo)
    }

    fun addPee(newPee: Pee) {
        AddPee(mPeeDao).execute(newPee)
    }

    fun getMostRecentCrate(): LiveData<Crate> {
        return GetMostRecentCrate(mCrateDao).execute().get()
    }

    fun getMostRecentFood(): LiveData<Food> {
        return GetMostRecentFood(mFoodDao).execute().get()
    }

    fun getMostRecentPee(): LiveData<Pee> {
        return GetMostRecentPee(mPeeDao).execute().get()
    }

    fun getMostRecentPoo(): LiveData<Poo> {
        return GetMostRecentPoo(mPooDao).execute().get()
    }

    class GetMostRecentPoo(mDao: PooDao) : AsyncTask<Void, Void, LiveData<Poo>>(){
        val mDao: PooDao
        init{
            this.mDao = mDao
        }

        override fun doInBackground(vararg voids: Void): LiveData<Poo>? {
            return mDao.getMostRecent()
        }
    }

    class GetMostRecentPee(mDao: PeeDao) : AsyncTask<Void, Void, LiveData<Pee>>(){
        val mDao: PeeDao
        init{
            this.mDao = mDao
        }

        override fun doInBackground(vararg voids: Void): LiveData<Pee>? {
            return mDao.getMostRecent()
        }
    }

    class GetMostRecentFood(mDao: FoodDao) : AsyncTask<Void, Void, LiveData<Food>>(){
        val mDao: FoodDao
        init{
            this.mDao = mDao
        }

        override fun doInBackground(vararg voids: Void): LiveData<Food>? {
            return mDao.getMostRecent()
        }
    }

    class GetMostRecentCrate(mDao: CrateDao) : AsyncTask<Void, Void, LiveData<Crate>>(){
        val mDao: CrateDao
        init{
            this.mDao = mDao
        }

        override fun doInBackground(vararg voids: Void): LiveData<Crate>? {
            return mDao.getMostRecent()
        }
    }

    class AddPoo(mDao: PooDao) : AsyncTask<Poo, Void, Void>(){
        val mDao: PooDao
        init{
            this.mDao = mDao
        }

        override fun doInBackground(vararg poos: Poo): Void? {
            mDao.add(poos[0])
            return null
        }

    }

    class AddPee(mDao: PeeDao) : AsyncTask<Pee, Void, Void>(){
        val mDao: PeeDao
        init{
            this.mDao = mDao
        }

        override fun doInBackground(vararg pees: Pee): Void? {
            mDao.add(pees[0])
            return null
        }

    }

    class AddFood(mDao: FoodDao) : AsyncTask<Food, Void, Void>(){
        val mDao: FoodDao
        init{
            this.mDao = mDao
        }

        override fun doInBackground(vararg foods: Food): Void? {
            mDao.add(foods[0])
            return null
        }

    }

    class AddCrate(mDao: CrateDao) : AsyncTask<Crate, Void, Void>(){
        val mDao: CrateDao
        init{
            this.mDao = mDao
        }

        override fun doInBackground(vararg crates: Crate): Void? {
            mDao.add(crates[0])
            return null
        }

    }

    class GetAllCrates(mDao: CrateDao) : AsyncTask<Void, Void, List<Crate>>(){
        val mDao: CrateDao
        init{
            this.mDao = mDao
        }
        override fun doInBackground(vararg params: Void?): List<Crate> {
            return mDao.getAll()
        }

    }

}
