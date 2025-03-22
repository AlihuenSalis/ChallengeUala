package ali.projects.challengeuala.data.network

import ali.projects.challengeuala.data.network.models.CityResponse
import retrofit2.http.GET

interface ApiClient {
    @GET("cities.json")
    suspend fun getCitiesList(): List<CityResponse>
}