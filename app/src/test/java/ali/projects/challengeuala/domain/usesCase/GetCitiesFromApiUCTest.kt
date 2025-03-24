package ali.projects.challengeuala.domain.usesCase

import ali.projects.challengeuala.domain.model.CityModel
import ali.projects.challengeuala.domain.repository.NetworkRepository
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test


class GetCitiesFromApiUCTest {
    private val networkRepository = mock<NetworkRepository>()
    private val getCitiesFromApiUC = GetCitiesFromApiUC(networkRepository)

    @Test
    fun `when the api return a value`() {
        runBlocking {
            val citiesList = listOf(
                CityModel(1, "Argentina", "AR", -31.4201, -64.1888, false),
                CityModel(2, "Roma", "IT", 41.9028, 12.4964, true),
                CityModel(3, "Brasilia", "BR", -15.7939, -47.8828, false)
            )

            whenever(networkRepository.getCitiesFromApi()) doReturn flowOf(citiesList)

            val result: List<CityModel> = getCitiesFromApiUC().toList().flatten()

            assertEquals(citiesList, result)
            verify(networkRepository).getCitiesFromApi()
        }
    }

    @Test
    fun `when the api return an empty list`() {
        runBlocking {
            whenever(networkRepository.getCitiesFromApi()) doReturn flowOf(emptyList())

            val result: List<CityModel> = getCitiesFromApiUC().toList().flatten()

            assertEquals(emptyList<CityModel>(), result)
            verify(networkRepository).getCitiesFromApi()
        }
    }
}