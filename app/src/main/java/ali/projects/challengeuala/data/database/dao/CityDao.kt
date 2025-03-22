package ali.projects.challengeuala.data.database.dao

import ali.projects.challengeuala.data.database.entities.CityEntity
import ali.projects.challengeuala.domain.model.CityModel
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cities: List<CityEntity>)

    @Query("SELECT COUNT(*) FROM city_entity_table")
    suspend fun getCitiesCount(): Int

    @Query("SELECT * FROM city_entity_table WHERE name LIKE :query || '%' ORDER BY name ASC LIMIT :limit OFFSET :offset")
    fun getCitiesByPrefix(query: String, limit: Int, offset: Int): Flow<List<CityEntity>>

    @Query("UPDATE city_entity_table SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateCityById(id: Int, isFavorite: Boolean)


    @Update
    suspend fun updateCitiesToNoFavorites(cities: List<CityEntity>)
}