package ali.projects.challengeuala.domain.usesCase

import ali.projects.challengeuala.domain.repository.DatabaseRepository
import javax.inject.Inject

class CheckDatabaseIfEmptyUC @Inject constructor(
    private val databaseRepository: DatabaseRepository
){
    suspend operator fun invoke(): Boolean {
        return databaseRepository.hasLocalCities()
    }
}