package com.jcromp.puppytracker.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.jcromp.puppytracker.db.entity.Poo

@Dao
interface PooDao {

    @Query("SELECT * FROM Poo")
    fun getAll(): List<Poo>

    @Insert
    fun add(poo: Poo)

    @Query("SELECT * FROM Poo ORDER BY time DESC LIMIT 1")
    fun getMostRecent() : LiveData<Poo>
}
