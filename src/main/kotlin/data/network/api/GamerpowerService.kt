package data.network.api

import data.network.response.GiveAwayItem
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface GamerpowerService {
    @GET("giveaways")
    suspend fun getAllGiveaways(): List<GiveAwayItem>

    @GET("giveaway")
    suspend fun getGiveawayDetails(@Query("id") id: Int): GiveAwayItem

    @GET("giveaways")
    suspend fun getFilteredGiveaways(@Query("platform") platform: String): List<GiveAwayItem>
}