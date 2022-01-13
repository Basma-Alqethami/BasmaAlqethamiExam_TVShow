package com.example.basmaalqethamiexamtvshow.Room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.basmaalqethamiexamtvshow.Model.Data

@Dao
interface ShowsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(show: Data)

    @Query("SELECT * FROM shows_table")
    fun getAll(): LiveData<List<Data>>

    @Update
    suspend fun update(show: Data)

    @Delete
    suspend fun delete(show: Data)
}