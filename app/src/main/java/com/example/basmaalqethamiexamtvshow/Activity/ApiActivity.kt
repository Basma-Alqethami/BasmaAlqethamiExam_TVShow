package com.example.basmaalqethamiexamtvshow.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basmaalqethamiexamtvshow.API.Api
import com.example.basmaalqethamiexamtvshow.Model.Data
import com.example.basmaalqethamiexamtvshow.API.Shows
import com.example.basmaalqethamiexamtvshow.Adapter.ApiAdapter
import com.example.basmaalqethamiexamtvshow.Model.ShowViewModel
import com.example.basmaalqethamiexamtvshow.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiActivity : AppCompatActivity() {

    var searchText = ""
    private var list = ArrayList<Data>()
    lateinit var viewModel: ShowViewModel

    private lateinit var rvAdapter: ApiAdapter
    private lateinit var recyclerView : RecyclerView
    private lateinit var ed_Name: EditText
    private lateinit var btnSearch: Button
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api)

        viewModel = ViewModelProvider(this).get(ShowViewModel::class.java)

        ed_Name = findViewById(R.id.ed_Name)
        btnSearch = findViewById(R.id.btnSearch)
        btnBack = findViewById(R.id.btnBack)

        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.rv_Api)
        rvAdapter = ApiAdapter(this, list)
        recyclerView.adapter = rvAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        btnSearch.setOnClickListener {
            searchText = ed_Name.text.toString()

            if (searchText.isNotEmpty()){
                ed_Name.clearFocus()
                ed_Name.text.clear()
                fetchDataByRetrofit()
            }
            else {
                Toast.makeText(this@ApiActivity, "Please enter something", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun fetchDataByRetrofit(){
        val url = "https://api.tvmaze.com/search/"
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api: Api = retrofit.create(Api::class.java)

        val urlId = "shows?q=$searchText"
        val callApi = api.getData(urlId)

        callApi.enqueue(object : Callback<Shows> {
            override fun onResponse(call: Call<Shows>, response: Response<Shows>) {
                Log.d("sssssssss", "${response.body()!!}")

                list.clear()

                for(item in response.body()!!){

                    val name = item.show.name
                    val language = item.show.language
                    var summary = ""
                    var image = ""

                    try{
                        summary = item.show.summary
                        image = item.show.image.original
                    }
                    catch (e: Exception)
                    {
                        summary = "No summary"
                        image = "No image"
                    }
                    list.add(Data(0,name, language, summary, image))
                }
                rvAdapter.notifyDataSetChanged()
                ed_Name.clearFocus()
            }

            override fun onFailure(call: Call<Shows>, t: Throwable) {
                Log.d("error", "something wrong. $t")
            }
        })
    }
}