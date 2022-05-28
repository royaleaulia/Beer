package com.example.beer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.beer.R
import com.example.beer.databinding.FragmentBeerListBinding

class BeerListFragment : Fragment() {

    private val viewModel: BeerViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBeerListBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = BeerListAdapter(BeerListener { beer ->
            viewModel.onBeerClicked(beer)
            findNavController()
                .navigate(R.id.action_beerListFragment_to_beerDetailFragment)
        })

        // Inflate the layout for this fragment
        return binding.root
    }
}