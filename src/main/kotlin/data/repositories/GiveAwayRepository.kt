package data.repositories

import data.network.api.GamerpowerService
import data.network.response.GiveAwayItem

class GiveAwayRepository(private val giveAwayService: GamerpowerService) {
    private val giveawaysList = mutableListOf<GiveAwayItem>()

    suspend fun fetchAllGiveaways() : List<GiveAwayItem> {
        if (giveawaysList.isNotEmpty()) return giveawaysList

        val giveaways = giveAwayService.getAllGiveaways()
        giveawaysList.addAll(giveaways)
        return giveaways
    }

    suspend fun fetchGiveawayDetails(id: Int) : GiveAwayItem {
        return giveAwayService.getGiveawayDetails(id)
    }
}