package com.jcromp.puppytracker.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.jcromp.puppytracker.db.entity.Food

@Dao
interface FoodDao {

    @Query("SELECT * FROM Food")
    fun getAll(): List<Food>

    @Insert
    fun add(food: Food)

    @Query("SELECT * FROM Food ORDER BY time DESC LIMIT 1")
    fun getMostRecent() : LiveData<Food>
}
