package com.example.basmaalqethamiexamtvshow.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.basmaalqethamiexamtvshow.API.Api
import com.example.basmaalqethamiexamtvshow.Model.Data
import com.example.basmaalqethamiexamtvshow.API.Shows
import com.example.basmaalqethamiexamtvshow.Adapter.ApiAdapter
import com.example.basmaalqethamiexamtvshow.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ApiFragment : Fragment() {

    var searchText = "girls"
    private var list = ArrayList<Data>()

    private lateinit var rvAdapter: ApiAdapter
    private lateinit var recyclerView : RecyclerView
    private lateinit var ed_Name: EditText
    private lateinit var btnSearch: Button
    private lateinit var btnBack: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_api, container, false)

        ed_Name = view.findViewById(R.id.ed_Name)
        btnSearch = view.findViewById(R.id.btnSearch)
        btnBack = view.findViewById(R.id.btnBack)

        btnBack.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_localFragment_to_mainFragment)
        }

//        recyclerView = view.findViewById(R.id.rv_Api)
//        rvAdapter = ApiAdapter(this, list)
//        recyclerView.adapter = rvAdapter
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        btnSearch.setOnClickListener {
            searchText = ed_Name.text.toString()

            if (searchText.isNotEmpty()){
                ed_Name.clearFocus()
                ed_Name.text.clear()
                fetchDataByRetrofit()
            }
            else {
                Toast.makeText(requireContext(), "Please enter something", Toast.LENGTH_SHORT).show()
            }
        }
        return view
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

            }

            override fun onFailure(call: Call<Shows>, t: Throwable) {
                Log.d("error", "something wrong. $t")
            }
        })
    }

}