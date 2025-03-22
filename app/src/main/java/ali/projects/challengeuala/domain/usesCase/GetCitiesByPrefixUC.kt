package ali.projects.challengeuala.domain.usesCase

import ali.projects.challengeuala.domain.model.CityModel
import ali.projects.challengeuala.domain.repository.DatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCitiesByPrefixUC @Inject constructor(
    private val databaseRepository: DatabaseRepository
){
    operator fun invoke(query: String, limit: Int, offset: Int): Flow<List<CityModel>> {
        return databaseRepository.getCitiesByPrefix(query, limit, offset)
    }
}