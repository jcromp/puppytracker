package com.jcromp.puppytracker.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Poo(@PrimaryKey(autoGenerate = true) var id: Long?,
               var time: Date? = null
)
{
    constructor(): this(null, Date())
}