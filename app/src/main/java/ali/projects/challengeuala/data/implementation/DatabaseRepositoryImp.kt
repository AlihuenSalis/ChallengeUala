package ali.projects.challengeuala.data.implementation

import ali.projects.challengeuala.data.database.dao.CityDao
import ali.projects.challengeuala.domain.model.CityModel
import ali.projects.challengeuala.domain.repository.DatabaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DatabaseRepositoryImp @Inject constructor(
    private val dao: CityDao
): DatabaseRepository {

    override suspend fun storeCities(cities: List<CityModel>) {
        withContext(Dispatchers.IO) {
            dao.insertAll(cities.map { it.toEntity() })
        }
    }

    override suspend fun hasLocalCities(): Boolean {
        return dao.getCitiesCount() > 0
    }

    override fun getCitiesByPrefix(
        query: String,
        limit: Int,
        offset: Int
    ): Flow<List<CityModel>> {
        return dao.getCitiesByPrefix(query, limit, offset)
            .map { it.cityEntityListToCityModelList() }
    }

    override suspend fun updateCityFavoriteById(id: Int, isFavorite: Boolean) {
        dao.updateCityById(id, isFavorite)
    }

    override suspend fun updateCitiesLikeNoFavorites(cities: List<CityModel>) {
        dao.updateCitiesToNoFavorites(cities.map { it.toEntity() })
    }
}