package com.jcromp.puppytracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.jcromp.puppytracker.db.entity.Crate
import com.jcromp.puppytracker.db.EntityRepo
import com.jcromp.puppytracker.db.entity.Food
import com.jcromp.puppytracker.db.entity.Pee
import com.jcromp.puppytracker.db.entity.Poo

class MainViewModel(app: Application) : AndroidViewModel(app){

    var mEntityRepo: EntityRepo

    init{
        mEntityRepo = EntityRepo(app)
    }

    fun getMostRecentCrateTime(): LiveData<Crate> {
        return mEntityRepo.getMostRecentCrate();
    }

    fun getMostRecentFoodTime(): LiveData<Food> {
        return mEntityRepo.getMostRecentFood();
    }

    fun getMostRecentPeeTime(): LiveData<Pee> {
        return mEntityRepo.getMostRecentPee();
    }

    fun getMostRecentPooTime(): LiveData<Poo> {
        return mEntityRepo.getMostRecentPoo();
    }

    fun getAllCrates() : List<Crate>{
        return mEntityRepo.getAllCrates()
    }

    fun addCrate(){
        var newCrate = Crate()
        mEntityRepo.addCrate(newCrate)
    }

    fun addFood(){
        var newFood = Food()
        mEntityRepo.addFood(newFood)
    }

    fun addPee(){
        var newPee = Pee()
        mEntityRepo.addPee(newPee)
    }

    fun addPoo(){
        var newPoo = Poo()
        mEntityRepo.addPoo(newPoo)
    }
}