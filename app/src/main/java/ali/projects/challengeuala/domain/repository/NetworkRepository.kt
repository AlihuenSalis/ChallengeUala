package ali.projects.challengeuala.domain.repository

import ali.projects.challengeuala.domain.model.CityModel
import kotlinx.coroutines.flow.Flow

interface NetworkRepository {
    suspend fun getCitiesFromApi(): Flow<List<CityModel>>
}