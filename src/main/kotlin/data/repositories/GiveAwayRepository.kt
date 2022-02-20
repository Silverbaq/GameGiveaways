package data.repositories

import data.network.api.GamerpowerService
import data.network.response.GiveAwayItem

class GiveAwayRepository(private val giveAwayService: GamerpowerService) {

    suspend fun fetchAllGiveaways() : List<GiveAwayItem> {
        val giveaways = giveAwayService.getAllGiveaways()
        giveaways.forEach { item -> println(item.title) }
        return giveaways
    }

    suspend fun fetchGiveawayDetails(id: Int) : GiveAwayItem {
        return giveAwayService.getGiveawayDetails(id)
    }
}