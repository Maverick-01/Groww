package com.maverick.groww.owner.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.maverick.groww.R
import com.maverick.groww.data.TopGainerLoser
import com.maverick.groww.databinding.ItemTopGainerLoserBinding

class TopGainerLoserAdapter(private val callback: (item: TopGainerLoser, action: String) -> (Unit)) :
    ListAdapter<TopGainerLoser, TopGainerLoserAdapter.TopGainerLoserViewHolder>(
        object : DiffUtil.ItemCallback<TopGainerLoser>() {
            override fun areItemsTheSame(
                oldItem: TopGainerLoser,
                newItem: TopGainerLoser
            ): Boolean {
                return oldItem.ticker == newItem.ticker
            }

            override fun areContentsTheSame(
                oldItem: TopGainerLoser,
                newItem: TopGainerLoser
            ): Boolean {
                return oldItem == newItem
            }
        }
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopGainerLoserViewHolder {
//        val binding =
//            ItemTopGainerLoserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_top_gainer_loser, parent, false)
//        return TopGainerLoserViewHolder(
//            parent.context.getSystemService(LayoutInflater::class.java).inflate(
//                R.layout.item_top_gainer_loser,
//                parent,
//                false
//            )
//        )
        return TopGainerLoserViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopGainerLoserViewHolder, position: Int) {
        holder.bind(getItem(position))
//        ItemTopGainerLoserBinding.bind(holder.itemView).apply {
//            data = getItem(position)
//            root.setOnClickListener {
//                callback.invoke(getItem(position), "onClick")
//            }
//        }
    }

    inner class TopGainerLoserViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val binding = ItemTopGainerLoserBinding.bind(itemView)
        fun bind(data: TopGainerLoser) {
            binding.data = data
            binding.root.setOnClickListener {
                callback.invoke(getItem(position), "onClick")
            }
        }
    }
}