package com.example.basmaalqethamiexamtvshow.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.example.basmaalqethamiexamtvshow.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MainFragment : Fragment() {

    private lateinit var btnApi: Button
    private lateinit var btnLocal: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        btnApi = view.findViewById(R.id.btnApi)
        btnLocal = view.findViewById(R.id.btnLocal)

        btnLocal.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_localFragment)
        }

        btnApi.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_apiFragment)
        }

        return view
    }

}