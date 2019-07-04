package com.jcromp.puppytracker.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jcromp.puppytracker.db.entity.Crate

@Dao
interface CrateDao {

    @Query("SELECT * FROM crate")
    fun getAll(): List<Crate>

    @Insert
    fun add(crate: Crate)

    @Query("SELECT * FROM crate ORDER BY time DESC LIMIT 1")
    fun getMostRecent() : LiveData<Crate>
}
