package com.example.web_app.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.web_app.R
import com.example.web_app.domain.entity.Brawler


class BrawlerAdapter(
    private var brawlerList: List<Brawler>,
    private val action: (Int) -> Unit
) : RecyclerView.Adapter<BrawlerAdapter.BrawlerHolder>() {
    class BrawlerHolder(
        itemView: View,
        private val action: (Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tv_brawler_name)
        private val ivBrawler: ImageView = itemView.findViewById(R.id.iv_brawler_photo)

        fun bind(response: Brawler) {
            tvName.text = response.name
            ivBrawler.setImageResource(response.imageId)
            itemView.setOnClickListener {
                action(response.id)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrawlerHolder {
        return BrawlerHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.brawler_item, parent, false), action
        )
    }

    override fun onBindViewHolder(holder: BrawlerHolder, position: Int) {
        holder.bind(brawlerList[position])
    }

    override fun getItemCount(): Int = brawlerList.size


}