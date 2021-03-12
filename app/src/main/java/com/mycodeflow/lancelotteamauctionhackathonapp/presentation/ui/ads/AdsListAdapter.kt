package com.mycodeflow.lancelotteamauctionhackathonapp.presentation.ui.ads

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycodeflow.lancelotteamauctionhackathonapp.R
import com.mycodeflow.lancelotteamauctionhackathonapp.data.models.Advertisement

class AdsListAdapter : RecyclerView.Adapter<AdItemViewHolder>() {

    private var advertisements: List<Advertisement> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdItemViewHolder {
        return AdItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.ad_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AdItemViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = advertisements.size
}

class AdItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

}