package com.example.roxanahayne.gymapp.data

import android.arch.persistence.room.*

@Dao
interface GymDAO {

    @Query("SELECT * FROM gym")
    fun findAllGyms(): List<Gym>

//    @Query("SELECT * FROM gym WHERE name LIKE :nameToFind")
//    fun findByName(nameToFind: String): List<Gym>

    @Insert
    fun insertGym(item: Gym) : Long

    @Delete
    fun deleteGym(item: Gym)

    @Update
    fun updateGym(item: Gym)

}