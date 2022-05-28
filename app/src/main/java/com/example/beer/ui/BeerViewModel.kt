package com.example.beer.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beer.network.Beer
import com.example.beer.network.BeerApi
import kotlinx.coroutines.launch
import java.lang.Exception

enum class BeerApiStatus { LOADING, ERROR, DONE }

class BeerViewModel : ViewModel() {

    private var _status = MutableLiveData<BeerApiStatus>()
    val status = _status
    private var _beerList = MutableLiveData<List<Beer>>()
    val beerList = _beerList
    private var _selectedBeer = MutableLiveData<Beer>()
    val selectedBeer = _selectedBeer

    init {
        getBeerList()
    }
    private fun getBeerList(){
        viewModelScope.launch {
            _status.value = BeerApiStatus.LOADING
            try {
                _beerList.value = BeerApi.retrofitService.getBeer()
                _status.value = BeerApiStatus.DONE
            }
            catch (e : Exception) {
                _status.value = BeerApiStatus.ERROR
                _beerList.value = listOf()
            }
        }
    }

    fun onBeerClicked(beer: Beer){
        _selectedBeer.value = beer
    }
}