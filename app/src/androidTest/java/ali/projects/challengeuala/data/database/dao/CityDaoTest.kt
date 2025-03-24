package ali.projects.challengeuala.data.database.dao

import ali.projects.challengeuala.data.database.AppDatabase
import ali.projects.challengeuala.data.database.entities.CityEntity
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class CityDaoTest {
    private lateinit var cityDao: CityDao
    private lateinit var appDatabase: AppDatabase

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().context
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        cityDao = appDatabase.cityDao()
    }

    @After
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    fun when_the_app_requests_the_list_of_cities_from_database_then_the_database_returns_a_cities_list() = runBlocking {
        val firstCityEntity = CityEntity(1, "Argentina", "AR", -31.4201, -64.1888, false)
        val secondCityEntity = CityEntity(2, "Roma", "IT", 41.9028, 12.4964, false)
        val thirdCityEntity = CityEntity(3, "Brasilia", "BR", -15.7939, -47.8828, false)

        val expectedList = listOf(firstCityEntity, secondCityEntity, thirdCityEntity)

        cityDao.insertAll(listOf(firstCityEntity, secondCityEntity, thirdCityEntity))

        val resultList = cityDao.getCitiesByPrefix("", 15, 0).first().sortedBy { it.id }

        Assert.assertEquals(expectedList, resultList)
    }

    @Test
    fun when_the_app_requests_the_list_of_cities_from_database_then_the_database_returns_an_empty_list() = runBlocking {
        val expectedList = emptyList<CityEntity>()

        val resultList = cityDao.getCitiesByPrefix("", 15, 0).first()

        Assert.assertEquals(expectedList, resultList)
    }
}