package com.example.beer.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.beer.databinding.ListViewItemBinding
import com.example.beer.network.Beer

class BeerListAdapter(val clickListener: BeerListener) :
    ListAdapter<Beer, BeerListAdapter.BeerViewHolder>(DiffCallback) {

    class BeerViewHolder(
        var binding: ListViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(clickListener: BeerListener, beer: Beer) {
            binding.beer = beer
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Beer>() {

        override fun areItemsTheSame(oldItem: Beer, newItem: Beer): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Beer, newItem: Beer): Boolean {
            return oldItem.tagline == newItem.tagline && oldItem.description == newItem.description
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BeerViewHolder(
            ListViewItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        val beer = getItem(position)
        holder.bind(clickListener, beer)
    }
}

class BeerListener(val clickListener: (beer: Beer) -> Unit) {
    fun onClick(beer: Beer) = clickListener(beer)
}