package com.example.beer

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.beer.network.Beer
import com.example.beer.ui.BeerApiStatus
import com.example.beer.ui.BeerListAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Beer>?) {
    val adapter = recyclerView.adapter as BeerListAdapter
    adapter.submitList(data)
}

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: BeerApiStatus?) {
    when(status) {
        BeerApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        BeerApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        BeerApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}