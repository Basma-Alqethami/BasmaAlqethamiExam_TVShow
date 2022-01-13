package com.example.basmaalqethamiexamtvshow.Model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.basmaalqethamiexamtvshow.Room.ShowsDao
import com.example.basmaalqethamiexamtvshow.Room.ShowsDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShowViewModel (application: Application): AndroidViewModel(application) {

    private val showsDao: ShowsDao
    private val shows: LiveData<List<Data>>

    init {
        showsDao = ShowsDatabase.getDatabase(application).show_Dao()
        shows = showsDao.getAll()
    }

    fun getShows(): LiveData<List<Data>> {
        return shows
    }

    fun delete(show: Data) {
        CoroutineScope(Dispatchers.IO).launch {
            showsDao.delete(show)
        }
    }

    fun add(show: Data) {
        CoroutineScope(Dispatchers.IO).launch {
            showsDao.add(show)
        }
    }
}