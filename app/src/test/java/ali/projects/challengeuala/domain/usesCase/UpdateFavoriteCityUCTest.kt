package ali.projects.challengeuala.domain.usesCase

import ali.projects.challengeuala.domain.repository.DatabaseRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test


class UpdateFavoriteCityUCTest {
    private val databaseRepository = mock<DatabaseRepository>()
    private val updateFavoritesCitiesUC = UpdateFavoriteCityUC(databaseRepository)

    @Test
    fun `updates favorite city in database to true`() {
        runBlocking {
            val cityId = 1
            val isFavorite = true

            updateFavoritesCitiesUC(cityId, isFavorite)

            verify(databaseRepository).updateCityFavoriteById (cityId, isFavorite)
        }
    }

    @Test
    fun `test execute updates city favorite status to false`() {
        runBlocking {
            val cityId = 2
            val isFavorite = false

            updateFavoritesCitiesUC(cityId, isFavorite)

            verify(databaseRepository).updateCityFavoriteById(cityId, isFavorite)
        }
    }
}