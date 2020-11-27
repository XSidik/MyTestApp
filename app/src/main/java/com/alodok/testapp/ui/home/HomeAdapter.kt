package com.alodok.testapp.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.alodok.testapp.R
import com.alodok.testapp.databinding.ListImageBinding
import com.alodok.testapp.detail.DetailActivity
import com.alodok.testapp.model.DataImage
import com.bumptech.glide.Glide

class HomeAdapter(private val imageList: List<DataImage>) :
    RecyclerView.Adapter<HomeAdapter.HomeHolder>() {

    class HomeHolder(private val itemBinding: ListImageBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(data: DataImage) {
            with(itemBinding) {
                Glide.with(itemView.context).load(data.image)
                    .fitCenter()
                    .placeholder(R.drawable.ic_home_black_24dp)
                    .into(imgList)

                imgList.setOnClickListener {
                    val da = Intent(itemView.context, DetailActivity::class.java)
                    startActivity(itemView.context, da, null)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        val itemBinding =
            ListImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        val imageList: DataImage = imageList[position]
        holder.bind(imageList)
    }

    override fun getItemCount(): Int = imageList.size

}
