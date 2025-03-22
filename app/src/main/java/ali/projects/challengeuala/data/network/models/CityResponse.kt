package ali.projects.challengeuala.data.network.models

import ali.projects.challengeuala.domain.model.CityModel
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CityResponse(
    @SerializedName("_id") val id: Int,
    @SerializedName("coord") val coordinates: Coordinates,
    val country: String,
    val name: String
) {
    fun toDomain(): CityModel {
        return CityModel(
            id = id,
            country = country,
            name = name,
            latitude = coordinates.latitude,
            longitude = coordinates.longitude
        )
    }
}