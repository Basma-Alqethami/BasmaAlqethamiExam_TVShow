package com.example.basmaalqethamiexamtvshow.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basmaalqethamiexamtvshow.Adapter.ApiAdapter
import com.example.basmaalqethamiexamtvshow.Adapter.LocalAdapter
import com.example.basmaalqethamiexamtvshow.Model.Data
import com.example.basmaalqethamiexamtvshow.Model.ShowViewModel
import com.example.basmaalqethamiexamtvshow.R

class LocalActivity : AppCompatActivity() {

    private var list: List<Data> = listOf()
    lateinit var viewModel: ShowViewModel

    private lateinit var btnBack: Button
    private lateinit var rvAdapter: LocalAdapter
    private lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local)

        viewModel = ViewModelProvider(this).get(ShowViewModel::class.java)

        btnBack = findViewById(R.id.btnBack)

        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.rv_local)
        viewModel = ViewModelProvider(this).get(ShowViewModel::class.java)
        viewModel.getShows().observe(this, {
                showsList -> list = showsList
            RVUpdate()
        })
    }

    fun RVUpdate() {
        rvAdapter = LocalAdapter(this, list)
        recyclerView.adapter = rvAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}