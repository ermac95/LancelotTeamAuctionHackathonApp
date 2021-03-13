package com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.create

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.mycodeflow.lancelotteamauctionhackathonapp.R
import com.mycodeflow.lancelotteamauctionhackathonapp.data.models.ItemImage

class NewItemImageSlideAdapter(private val gallery: PicturesGallery): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val images: ArrayList<ItemImage> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == DEFAULT_IMAGE_TYPE){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.empty_picture_card, parent, false)
            DefaultViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.new_pic_card, parent, false)
            NewImageViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == DEFAULT_IMAGE_TYPE){
            (holder as DefaultViewHolder).itemView.setOnClickListener{
                gallery.openGallery()
            }
        } else {
            (holder as NewImageViewHolder).onBind(images[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == images.size - 1){
            DEFAULT_IMAGE_TYPE
        } else {
            NEW_IMAGE_TYPE
        }
    }

    override fun getItemCount(): Int = images.size

    fun addNewItem(newItem: ItemImage) {
        images.add(0, newItem)
        notifyDataSetChanged()
    }

    interface PicturesGallery{
        fun openGallery()
    }

    fun getImages(): ArrayList<ItemImage> = images

    companion object {
        private const val DEFAULT_IMAGE_TYPE: Int = 0
        private const val NEW_IMAGE_TYPE: Int = 1
    }
}

class DefaultViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

}

class NewImageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    private val image = itemView.findViewById<ImageView>(R.id.bg_description_image)

    fun onBind(item: ItemImage){
        image.setImageURI(item.bgImage?.toUri())
    }
}