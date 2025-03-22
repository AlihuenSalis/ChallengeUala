package ali.projects.challengeuala.domain.model

import ali.projects.challengeuala.data.database.entities.CityEntity

data class CityModel (
    val id: Int,
    val name: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val isFavorite: Boolean = false
) {
    fun toEntity(): CityEntity {
        return CityEntity(
            id = id,
            name = name,
            country = country,
            latitude = latitude,
            longitude = longitude,
            isFavorite = isFavorite
        )
    }
}
