package data.repositories

import data.network.api.GamerpowerService
import data.network.response.GiveAwayItem

class GiveAwayRepository(private val giveAwayService: GamerpowerService) {
    private val giveawaysList = mutableListOf<GiveAwayItem>()

    suspend fun fetchAllGiveaways(): List<GiveAwayItem> {
        val giveaways = giveAwayService.getAllGiveaways()
        giveawaysList.addAll(giveaways)
        return giveaways
    }

    suspend fun fetchGiveawayDetails(id: Int): GiveAwayItem {
        return giveAwayService.getGiveawayDetails(id)
    }

    suspend fun fetchGiveaways(platforms: List<String>): List<GiveAwayItem> {
        val params = platforms.joinToString { "$it." }.dropLast(1)
        val giveaways = giveAwayService.getFilteredGiveaways(params)
        giveawaysList.clear()
        giveawaysList.addAll(giveaways)
        return giveawaysList
    }

    // fun getFilt
}
