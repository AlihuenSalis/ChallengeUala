package ali.projects.challengeuala.presentation.viewModels

import ali.projects.challengeuala.domain.model.CityModel
import ali.projects.challengeuala.domain.usesCase.CheckDatabaseIfEmptyUC
import ali.projects.challengeuala.domain.usesCase.GetCitiesByPrefixUC
import ali.projects.challengeuala.domain.usesCase.GetCitiesFromApiUC
import ali.projects.challengeuala.domain.usesCase.StoreCitiesUC
import ali.projects.challengeuala.domain.usesCase.UpdateCitiesLikeNoFavoritesUC
import ali.projects.challengeuala.domain.usesCase.UpdateFavoriteCityUC
import ali.projects.challengeuala.presentation.core.UIState
import ali.projects.challengeuala.presentation.utils.Constants
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(
    private val getCitiesFromApiUC: GetCitiesFromApiUC,
    private val checkDatabaseIfEmptyUC: CheckDatabaseIfEmptyUC,
    private val storeCitiesUC: StoreCitiesUC,
    private val getCitiesByPrefixUC: GetCitiesByPrefixUC,
    private val updateFavoriteCityUC: UpdateFavoriteCityUC,
    private val updateCitiesLikeNoFavoritesUC: UpdateCitiesLikeNoFavoritesUC
) : ViewModel() {

    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState: StateFlow<UIState> = _uiState

    private val _allCities = MutableStateFlow<List<CityModel>>(emptyList())
    private val _filteredCities = MutableStateFlow<List<CityModel>>(emptyList())
    val cities: StateFlow<List<CityModel>> = _filteredCities

    private val _citiesMarkLikeNotFavorites = MutableStateFlow<List<CityModel>> (emptyList())

    private var currentPage = 0
    private var isLastPage = false

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _isFavouritesOnly = MutableStateFlow(false)
    val isFavouritesOnly = _isFavouritesOnly.asStateFlow()

    init {
        getCities()
    }

    private fun observeSearchQuery() {
        viewModelScope.launch {
            _searchQuery.collect { query ->
                resetPagination()
                _filteredCities.value = filterCitiesByQuery(query, _allCities.value)
                if (!_isFavouritesOnly.value) {
                    if (_filteredCities.value.isEmpty() && !isLastPage) {
                        loadNextPage()
                    }
                }

            }
        }
    }

    private fun getCities() {
        viewModelScope.launch {
            try {
                val hasLocalData = checkDatabaseIfEmptyUC.invoke()
                if (!hasLocalData) {
                    getCitiesFromApi()
                } else {
                    getCitiesFromDatabase()
                }
            } catch (e: Exception) {
                Log.e("CityViewModel", "Get cities Error", e)
            }
        }
    }

    private fun getCitiesFromDatabase() {
        observeSearchQuery()
        viewModelScope.launch {
            getCitiesByPrefixUC.invoke(
                _searchQuery.value,
                Constants.PAGE_SIZE,
                currentPage * Constants.PAGE_SIZE
            )
                .collect { cityList ->
                    if (cityList.size < Constants.PAGE_SIZE) {
                        isLastPage = true
                    }
                    _allCities.value = (_allCities.value + cityList).distinctBy { it.id }
                    _filteredCities.value =
                        filterCitiesByQuery(_searchQuery.value, _allCities.value)
                    updateState()
                }
        }
    }

    private fun updateState() {
        _uiState.value = if (_filteredCities.value.isEmpty()) {
            UIState.Empty
        } else {
            UIState.Success(_filteredCities.value)
        }
    }

    private fun getCitiesFromApi() {
        viewModelScope.launch {
            getCitiesFromApiUC().collect { cities ->
                storeCitiesUC(cities)
                getCitiesFromDatabase()
            }
        }
    }

    fun onToggleFavorite(city: CityModel) {
        viewModelScope.launch {
            val id = city.id
            val isFavorite = !city.isFavorite

            if (_isFavouritesOnly.value) {
                _citiesMarkLikeNotFavorites.value += city.copy(isFavorite = false)
                _allCities.value -= _allCities.value.first { it.id == city.id }
            } else {
                updateFavoriteCityUC(city.id, !city.isFavorite)
                _allCities.value = _allCities.value.map { existingCity ->
                    if (existingCity.id == id) {
                        existingCity.copy(isFavorite = isFavorite)
                    } else {
                        existingCity
                    }
                }
            }
            _filteredCities.value = filterCitiesByQuery(_searchQuery.value, _allCities.value)
        }
    }

    private fun filterCitiesByQuery(query: String, cities: List<CityModel>): List<CityModel> {
        return if (query.isBlank()) cities
        else cities.filter { it.name.lowercase().startsWith(query, ignoreCase = true) }
    }

    fun updateSearchQuery(query: String) {
        if (_searchQuery.value != query) {
            _searchQuery.value = query
            resetPagination()
            _filteredCities.value = filterCitiesByQuery(query, _allCities.value)
            if (!isFavouritesOnly.value) {
                if (_filteredCities.value.isEmpty() && !isLastPage) {
                    loadNextPage()
                }
            }
        }
    }

    private fun resetPagination() {
        currentPage = 0
        isLastPage = false
    }

    fun loadNextPage() {
        if (!isLastPage) {
            currentPage++
            viewModelScope.launch {
                getCitiesByPrefixUC(
                    _searchQuery.value,
                    Constants.PAGE_SIZE,
                    currentPage * Constants.PAGE_SIZE
                )
                    .collect { cityList ->
                        if (cityList.size < Constants.PAGE_SIZE) {
                            isLastPage = true
                        }
                        _allCities.value = (_allCities.value + cityList).distinctBy { it.id }
                        _filteredCities.value =
                            filterCitiesByQuery(_searchQuery.value, _allCities.value)
                    }
            }
        }
    }

    fun showOnlyFavorites(isFavorite: Boolean) {
        _isFavouritesOnly.value = isFavorite
        if (isFavorite) {
            _allCities.value = _allCities.value.filter { it.isFavorite }
            _filteredCities.value = filterCitiesByQuery(_searchQuery.value, _allCities.value)
            updateState()
        } else {
            viewModelScope.launch {
                if (_citiesMarkLikeNotFavorites.value.isNotEmpty()) {
                    updateCitiesLikeNoFavoritesUC(_citiesMarkLikeNotFavorites.value)
                }
                resetPagination()
                _allCities.value = emptyList()
                _filteredCities.value = emptyList()
                _searchQuery.value = ""
                getCitiesFromDatabase()
            }
        }

    }

}