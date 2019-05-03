package com.jcromp.puppytracker.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class Pee(@PrimaryKey(autoGenerate = true) var id: Long?,
               var time: Date? = null)
{
    constructor(): this(null, Date())
}