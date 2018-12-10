package com.example.roxanahayne.gymapp.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "gym")
data class Gym(

    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = "name") var name: String
//    @ColumnInfo(name = "address") var address: String,
//    @ColumnInfo(name = "hours") var hours: String,
//    @ColumnInfo(name = "phone") var phone: String,
//    @ColumnInfo(name = "website") var website: String,
//    @ColumnInfo(name = "costDay") var costDay: Float,
//    @ColumnInfo(name = "costWeek") var costWeek: Float,
//    @ColumnInfo(name = "costMonth") var costMonth: Float,
//    @ColumnInfo(name = "costMonth6") var costMonth6: Float,
//    @ColumnInfo(name = "costYear") var costYear: Float

) : Serializable