package ali.projects.challengeuala.data.implementation

import ali.projects.challengeuala.data.database.entities.CityEntity
import ali.projects.challengeuala.domain.model.CityModel

fun List<CityEntity>.cityEntityListToCityModelList(): List<CityModel> {
    return this.map {
        CityModel(
            id = it.id,
            name = it.name,
            country = it.country,
            latitude = it.latitude,
            longitude = it.longitude,
            isFavorite = it.isFavorite
        )
    }
}