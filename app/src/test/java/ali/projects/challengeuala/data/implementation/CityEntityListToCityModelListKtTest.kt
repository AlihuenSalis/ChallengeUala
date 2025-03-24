package ali.projects.challengeuala.data.implementation

import ali.projects.challengeuala.data.database.entities.CityEntity
import ali.projects.challengeuala.domain.model.CityModel
import org.junit.Assert.assertEquals
import org.junit.Test


class CityEntityListToCityModelListKtTest {
    @Test
    fun `test when the cityEntityToPresentable have a non-empty list`() {
        val cityEntities = listOf(
            CityEntity(1, "Argentina", "AR", -31.4201, -64.1888, false),
            CityEntity(2, "Roma", "IT", 41.9028, 12.4964, true),
            CityEntity(3, "Brasilia", "BR", -15.7939, -47.8828, false)
        )

        val expected = listOf(
            CityModel(1, "Argentina", "AR", -31.4201, -64.1888, false),
            CityModel(2, "Roma", "IT", 41.9028, 12.4964, true),
            CityModel(3, "Brasilia", "BR", -15.7939, -47.8828, false)
        )

        val result = cityEntities.cityEntityListToCityModelList()

        assertEquals(expected, result)
    }

    @Test
    fun `test when the cityEntityToPresentable have empty list`() {
        //Given
        val cityEntities = emptyList<CityEntity>()
        val expected = emptyList<CityModel>()
        //When
        val result = cityEntities.cityEntityListToCityModelList()
        //Then
        assertEquals(expected, result)
    }
}