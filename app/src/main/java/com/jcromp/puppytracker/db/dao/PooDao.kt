package com.jcromp.puppytracker.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
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
