package com.example.basmaalqethamiexamtvshow.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.basmaalqethamiexamtvshow.Activity.LocalActivity
import com.example.basmaalqethamiexamtvshow.Model.Data
import com.example.basmaalqethamiexamtvshow.R
import kotlinx.android.synthetic.main.row_local.view.*
import androidx.appcompat.app.AlertDialog


class LocalAdapter (private val localActivity: LocalActivity, private var list: List<Data>): RecyclerView.Adapter<LocalAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_local,
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
                Glide.with(localActivity).load(data.image).into(imageView)
            }

            holder.itemView.btnDelete.setOnClickListener{
                localActivity.viewModel.delete(Data(data.id, data.name, data.language, data.summary, data.image))
                Toast.makeText(localActivity, "Deleted from local", Toast.LENGTH_SHORT).show()
            }

            holder.itemView.setOnClickListener{
                list[position].summary?.let { it1 -> showAlert(it1) }
            }
        }
    }

    override fun getItemCount() = list.size

    private fun showAlert(message: String){
        val dialogBuilder = AlertDialog.Builder(localActivity)
        dialogBuilder.setMessage(message)
            .setNegativeButton("Close") { dialog, _ ->
                dialog.cancel()
            }
        val alert = dialogBuilder.create()
        alert.setTitle("Summary")
        alert.show()
    }
}