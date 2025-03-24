package ali.projects.challengeuala.domain.usesCase

import ali.projects.challengeuala.domain.model.CityModel
import ali.projects.challengeuala.domain.repository.NetworkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCitiesFromApiUC @Inject constructor(
    private val networkRepository: NetworkRepository
){
    suspend operator fun invoke(): Flow<List<CityModel>> {
        return networkRepository.getCitiesFromApi()
    }
}