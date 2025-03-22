package ali.projects.challengeuala.data.implementation

import ali.projects.challengeuala.data.network.ApiClient
import ali.projects.challengeuala.domain.model.CityModel
import ali.projects.challengeuala.domain.repository.NetworkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetworkRepositoryImp @Inject constructor(
    private val apiClient: ApiClient
): NetworkRepository {

    override suspend fun getCitiesFromApi(): Flow<List<CityModel>> {
        return flow {
            val response = apiClient.getCitiesList()
            emit(response.map { it.toDomain() })
        }

    }
}