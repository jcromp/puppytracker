package com.jcromp.puppytracker.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import java.util.*

@Entity
data class Crate(@PrimaryKey(autoGenerate = true) var id: Long?,
    var time: Date? = null
)
{
    constructor(): this(null, Date())
}