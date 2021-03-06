package com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.ads

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mycodeflow.lancelotteamauctionhackathonapp.R
import com.mycodeflow.lancelotteamauctionhackathonapp.data.models.Advertisement
import com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.BaseFragment

class AdsListAdapter(
        private var listener: BaseFragment.HomeScreenActions?
) : RecyclerView.Adapter<AdItemViewHolder>() {

    private var advertisements: List<Advertisement> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdItemViewHolder {
        return AdItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.ad_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AdItemViewHolder, position: Int) {
        holder.onBind(advertisements[position])
        holder.itemView.setOnClickListener {
            listener?.navigateToDetails(advertisements[position].id)
        }
    }

    override fun getItemCount(): Int = advertisements.size

    fun onBindAds(ads: List<Advertisement>) {
        advertisements = ads
        notifyDataSetChanged()
    }
}

class AdItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val itemImage: ImageView = itemView.findViewById(R.id.front_image)
    private val itemText: TextView = itemView.findViewById(R.id.front_text)
    private val itemPrice: TextView = itemView.findViewById(R.id.front_price)
    private val startTime: TextView = itemView.findViewById(R.id.front_date)

    fun onBind(item: Advertisement){
        Glide.with(itemView.context)
            .load(item.poster)
            .into(itemImage)
        itemText.text = item.title
        val price = "${item.price.toInt()} RUB"
        itemPrice.text = price
        startTime.text = "Start on: ${item.date}"
    }
}