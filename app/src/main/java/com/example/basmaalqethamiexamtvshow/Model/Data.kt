package com.example.basmaalqethamiexamtvshow.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "shows_table")
data class Data(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val language: String,
    val summary: String?,
    val image: String): Serializable