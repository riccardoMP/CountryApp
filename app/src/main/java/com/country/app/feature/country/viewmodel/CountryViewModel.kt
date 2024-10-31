package com.country.app.feature.country.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.country.app.feature.country.domain.interactor.CountryInteractor
import com.country.app.feature.country.domain.model.CountryData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class CountryViewModel @Inject constructor(private val interactor: CountryInteractor) :
    ViewModel() {

    private var countryDataList: List<CountryData> = listOf()


    private val _countryList = MutableStateFlow<List<CountryData>>(listOf())
    val countryList: StateFlow<List<CountryData>> = _countryList


    fun loadData() {
        viewModelScope.launch {
            interactor.loadData().collect {
                countryDataList = it
                _countryList.value = it
            }
        }
    }

    fun filterData(query: String) {
        viewModelScope.launch {
            interactor.filterData(currentList = countryDataList, query = query).collect {
                Log.d("TAG", "_countryList: ${_countryList.value.size}")
                _countryList.value = it
            }
        }
    }

}
