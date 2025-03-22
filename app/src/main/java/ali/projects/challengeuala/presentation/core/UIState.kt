package ali.projects.challengeuala.presentation.core

import ali.projects.challengeuala.domain.model.CityModel

sealed class UIState {
    data object Loading : UIState()
    data object Empty : UIState()
    data class Success(val cities: List<CityModel>) : UIState()
}