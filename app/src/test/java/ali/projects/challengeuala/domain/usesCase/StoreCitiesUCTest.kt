package ali.projects.challengeuala.domain.usesCase

import ali.projects.challengeuala.domain.model.CityModel
import ali.projects.challengeuala.domain.repository.DatabaseRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test


class StoreCitiesUCTest {
    private val databaseRepository = mock<DatabaseRepository>()
    private val soreCitiesUC = StoreCitiesUC(databaseRepository)

    @Test
    fun `test execute saves city entities`() {
        runBlocking {
            val citiesList = listOf(
                CityModel(1, "Argentina", "AR", -31.4201, -64.1888, false),
                CityModel(2, "Roma", "IT", 41.9028, 12.4964, true),
                CityModel(3, "Brasilia", "BR", -15.7939, -47.8828, false)
            )

            soreCitiesUC(citiesList)

            verify(databaseRepository).storeCities(citiesList)
        }
    }

    @Test
    fun `test execute with empty list`() {
        runBlocking {
            val citiesModel = emptyList<CityModel>()

            soreCitiesUC(citiesModel)

            verify(databaseRepository).storeCities(citiesModel)
        }
    }
}