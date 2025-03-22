package ali.projects.challengeuala.domain.usesCase

import ali.projects.challengeuala.domain.model.CityModel
import ali.projects.challengeuala.domain.repository.DatabaseRepository
import javax.inject.Inject

class UpdateCitiesLikeNoFavoritesUC@Inject constructor(
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(cities: List<CityModel>) {
        databaseRepository.updateCitiesLikeNoFavorites(cities)
    }
}