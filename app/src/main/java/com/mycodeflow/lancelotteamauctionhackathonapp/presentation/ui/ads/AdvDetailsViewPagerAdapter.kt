package com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.ads

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mycodeflow.lancelotteamauctionhackathonapp.R

class AdvDetailsViewPagerAdapter : RecyclerView.Adapter<DetailsPagerViewHolder>() {

    private var images: List<String> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsPagerViewHolder {
        return DetailsPagerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.image_item_details, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DetailsPagerViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size

    fun bindImages(newImages: List<String>) {
        images = newImages
        notifyDataSetChanged()
    }

}

class DetailsPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val ivItem: ImageView = itemView.findViewById(R.id.ivDetailsItem)

    fun bind(itemImage: String) {
        Glide.with(itemView)
            .load(itemImage)
            .placeholder(R.drawable.ic_image_24)
            .into(ivItem)
    }
}