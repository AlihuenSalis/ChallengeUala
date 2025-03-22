package ali.projects.challengeuala.data.database.entities

import ali.projects.challengeuala.domain.model.CityModel
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_entity_table")
data class CityEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val isFavorite: Boolean = false
) {
    fun toDomain(): CityModel {
        return CityModel(
            id = id,
            name = name,
            country = country,
            latitude = latitude,
            longitude = longitude,
            isFavorite = isFavorite
        )

    }
}