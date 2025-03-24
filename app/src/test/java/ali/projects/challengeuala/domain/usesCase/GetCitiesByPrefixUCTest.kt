package ali.projects.challengeuala.domain.usesCase

import ali.projects.challengeuala.domain.model.CityModel
import ali.projects.challengeuala.domain.repository.DatabaseRepository
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test


class GetCitiesByPrefixUCTest {
    private val databaseRepository = mock<DatabaseRepository>()
    private val getCitiesFromApiUC = GetCitiesByPrefixUC(databaseRepository)

    @Test
    fun `when the database Return a city model list`() {
        runBlocking {
            val citiesList = listOf(
                CityModel(1, "Argentina", "AR", -31.4201, -64.1888, false),
                CityModel(2, "Roma", "IT", 41.9028, 12.4964, true),
                CityModel(3, "Brasilia", "BR", -15.7939, -47.8828, false)
            )

            whenever(databaseRepository.getCitiesByPrefix("", 10, 0)) doReturn flowOf(citiesList)

            val result: List<CityModel> = getCitiesFromApiUC("", 10, 0).toList().flatten()

            assertEquals(citiesList, result)
            verify(databaseRepository).getCitiesByPrefix("", 10, 0)
        }
    }

    @Test
    fun `when the database doesNot Return anything`() {
        runBlocking {
            whenever(databaseRepository.getCitiesByPrefix("", 10, 0)) doReturn flowOf(emptyList())

            val result: List<CityModel> = getCitiesFromApiUC("", 10, 0).toList().flatten()

            assertEquals(emptyList<CityModel>(), result)
            verify(databaseRepository).getCitiesByPrefix("", 10, 0)
        }
    }
}