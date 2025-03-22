package ali.projects.challengeuala.domain.usesCase

import ali.projects.challengeuala.domain.repository.DatabaseRepository
import javax.inject.Inject

class UpdateFavoriteCityUC @Inject constructor(
private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(id: Int, isFavorite: Boolean) {
        databaseRepository.updateCityFavoriteById(id, isFavorite)
    }
}