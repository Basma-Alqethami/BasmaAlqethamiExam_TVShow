package com.example.basmaalqethamiexamtvshow.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.basmaalqethamiexamtvshow.Model.Data
import com.example.basmaalqethamiexamtvshow.Activity.ApiActivity
import com.example.basmaalqethamiexamtvshow.R
import kotlinx.android.synthetic.main.row_api.view.*
import kotlinx.android.synthetic.main.row_api.view.tv_ShowName
import kotlinx.android.synthetic.main.row_local.view.*

class ApiAdapter (private val apiActivity: ApiActivity, private var list: ArrayList<Data>): RecyclerView.Adapter<ApiAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_api,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val data = list[position]

        holder.itemView.apply {
            tv_ShowName.text = data.name

            if (!data.image.equals("No image")){
                Glide.with(apiActivity).load(data.image).into(imageV)
            }

            holder.itemView.btnAdd.setOnClickListener{
                apiActivity.viewModel.add(Data(data.id, data.name, data.language, data.summary, data.image))
                Toast.makeText(apiActivity, "Add to local", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount() = list.size
}