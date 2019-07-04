package com.jcromp.puppytracker.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jcromp.puppytracker.db.entity.Pee

@Dao
interface PeeDao {

    @Query("SELECT * FROM Pee")
    fun getAll(): List<Pee>

    @Insert
    fun add(pee: Pee)

    @Query("SELECT * FROM Pee ORDER BY time DESC LIMIT 1")
    fun getMostRecent() : LiveData<Pee>
}
