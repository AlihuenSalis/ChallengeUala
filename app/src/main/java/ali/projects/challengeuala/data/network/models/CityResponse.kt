package ali.projects.challengeuala.data.network.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CityResponse(
    @SerializedName("_id") val id: Int,
    @SerializedName("coord") val coordinates: Coordinates,
    val country: String,
    val name: String
)