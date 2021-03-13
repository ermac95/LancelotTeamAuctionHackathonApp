package com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.ads

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mycodeflow.lancelotteamauctionhackathonapp.R
import com.mycodeflow.lancelotteamauctionhackathonapp.data.models.Advertisement

class AdsListAdapter : RecyclerView.Adapter<AdItemViewHolder>() {

    private var advertisements: List<Advertisement> = listOf(Advertisement(
        title = "Aaaaaaaaaaaaaaaa"
    ))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdItemViewHolder {
        return AdItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.ad_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AdItemViewHolder, position: Int) {
        holder.onBind(advertisements[position])
    }

    override fun getItemCount(): Int = advertisements.size

    fun onBindAds(ads: List<Advertisement>) {
        notifyDataSetChanged()
    }
}

class AdItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val itemImage: ImageView = itemView.findViewById(R.id.front_image)
    private val itemText: TextView = itemView.findViewById(R.id.front_text)

    fun onBind(item: Advertisement){
        itemText.text = item.title
    }
}