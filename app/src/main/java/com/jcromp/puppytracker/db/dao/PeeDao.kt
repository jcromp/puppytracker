package com.jcromp.puppytracker.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
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
