package com.example.basmaalqethamiexamtvshow.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.basmaalqethamiexamtvshow.Model.Data


@Database(entities = [Data::class], version = 1, exportSchema = false)
abstract class ShowsDatabase: RoomDatabase() {

    abstract fun show_Dao(): ShowsDao

    companion object{
        @Volatile
        private var INSTANCE: ShowsDatabase? = null

        fun getDatabase(context: Context): ShowsDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShowsDatabase::class.java,
                    "notes"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}