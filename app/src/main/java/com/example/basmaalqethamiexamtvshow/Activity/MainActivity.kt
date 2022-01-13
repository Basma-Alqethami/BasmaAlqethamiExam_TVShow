package com.example.basmaalqethamiexamtvshow.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.basmaalqethamiexamtvshow.R

class MainActivity : AppCompatActivity() {

    private lateinit var btnApi: Button
    private lateinit var btnLocal: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnApi = findViewById(R.id.btnApi)
        btnLocal = findViewById(R.id.btnLocal)

        btnLocal.setOnClickListener {
            val intent = Intent(this, LocalActivity::class.java)
            startActivity(intent)
        }

        btnApi.setOnClickListener {
            val intent = Intent(this, ApiActivity::class.java)
            startActivity(intent)
        }

    }
}