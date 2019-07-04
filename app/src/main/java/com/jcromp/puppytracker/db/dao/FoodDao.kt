package com.jcromp.puppytracker.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
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
