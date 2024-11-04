package com.country.app.feature.country.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.country.app.feature.country.domain.usecase.CountryUseCase
import com.country.app.feature.country.domain.usecase.state.CountryStateDomain.DataError
import com.country.app.feature.country.domain.usecase.state.CountryStateDomain.DataReady
import com.country.app.feature.country.domain.usecase.state.CountryStateDomain.Loading
import com.country.app.feature.country.viewmodel.state.CountryUIState
import com.country.app.feature.country.viewmodel.state.CountryUIState.OnDataError
import com.country.app.feature.country.viewmodel.state.CountryUIState.OnDataReady
import com.country.app.feature.country.viewmodel.state.CountryUIState.OnLoading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
@OptIn(FlowPreview::class)
class CountryViewModel @Inject constructor(private val useCase: CountryUseCase) :
    ViewModel() {

    private val searchQueryStateFlow = MutableStateFlow("")
    // Expose the search query as a StateFlow
    val searchQuery: StateFlow<String> = searchQueryStateFlow

    @OptIn(ExperimentalCoroutinesApi::class)
    val countryUIState: StateFlow<CountryUIState> = searchQueryStateFlow
        .debounce(300)
        .flatMapLatest { query ->
            useCase.filterData(query).map { state ->

                when (state) {
                    is Loading -> OnLoading
                    is DataReady -> {
                        OnDataReady(state.data)
                    }
                    is DataError -> OnDataError(state.error)
                }

            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), OnLoading)

    fun updateSearchQuery(query: String) {
        searchQueryStateFlow.value = query
    }
}
