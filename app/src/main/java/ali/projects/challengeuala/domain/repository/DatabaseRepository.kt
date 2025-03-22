package ali.projects.challengeuala.domain.repository

import ali.projects.challengeuala.domain.model.CityModel
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {
    suspend fun storeCities(cities: List<CityModel>)
    suspend fun hasLocalCities(): Boolean
    fun getCitiesByPrefix(query: String, limit: Int, offset: Int): Flow<List<CityModel>>
    suspend fun updateCityFavoriteById(id: Int, isFavorite: Boolean)
    suspend fun updateCitiesLikeNoFavorites(cities: List<CityModel>)
}