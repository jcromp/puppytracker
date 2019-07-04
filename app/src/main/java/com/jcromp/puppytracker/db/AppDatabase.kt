package com.jcromp.puppytracker.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jcromp.puppytracker.db.dao.CrateDao
import com.jcromp.puppytracker.db.dao.FoodDao
import com.jcromp.puppytracker.db.dao.PeeDao
import com.jcromp.puppytracker.db.dao.PooDao
import com.jcromp.puppytracker.db.entity.Crate
import com.jcromp.puppytracker.db.entity.Food
import com.jcromp.puppytracker.db.entity.Pee
import com.jcromp.puppytracker.db.entity.Poo

@Database(entities = arrayOf(Crate::class, Food::class, Poo::class, Pee::class),
    version = 1)
@TypeConverters(TimestampConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getCrateDao(): CrateDao
    abstract fun getFoodDao(): FoodDao
    abstract fun getPeeDao(): PeeDao
    abstract fun getPooDao(): PooDao

    companion object {
        const val DB_NAME = "appdb.db"

        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if(INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase::class.java, DB_NAME)
                        .build()
                }
            }
            return INSTANCE!!
        }
    }

}