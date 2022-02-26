package ui.giveaways

import androidx.compose.runtime.mutableStateOf
import data.network.response.GiveAwayItem
import data.repositories.GiveAwayRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ui.giveaways.filter.Filter
import utils.GiveawayFilters

class GiveawayViewModel(private val giveAwayRepository: GiveAwayRepository) {
    private val scope = CoroutineScope(SupervisorJob())
    private val platforms = mutableListOf<String>()

    private val providerFilter = listOf(
        Filter("Epic", false, GiveawayFilters.EPIC_GAMES_STORE),
        Filter("Ubisoft", false, GiveawayFilters.UBISOFT),
        Filter("GOG", false, GiveawayFilters.GOG),
        Filter("Itch.io", false, GiveawayFilters.ITCHIO),
        Filter("Battle.net", false, GiveawayFilters.BATTLENET),
        Filter("Origin", false, GiveawayFilters.ORIGIN),
    )

    private val platformFilters = listOf(
        Filter("PC", false, GiveawayFilters.PC),
        Filter("Android", false, GiveawayFilters.ANDROID),
        Filter("iOS", false, GiveawayFilters.IOS),
        Filter("Switch", false, GiveawayFilters.SWITCH),
        Filter("PS4", false, GiveawayFilters.PS4),
        Filter("PS5", false, GiveawayFilters.PS5),
        Filter("Xbox 360", false, GiveawayFilters.XBOX_360),
        Filter("Xbox One", false, GiveawayFilters.XBOX_ONE),
        Filter("Xbox Series XS", false, GiveawayFilters.XBOX_SERIES_XS),
    )

    val filterMap = mapOf(
        "Platforms" to platformFilters,
        "Providers" to providerFilter
    )

    val giveawaysState = mutableStateOf<List<GiveAwayItem>>(emptyList())

    init {
        fetchAll()
    }

    private fun fetchAll() {
        scope.launch {
            val giveaways = giveAwayRepository.fetchAllGiveaways()
            giveawaysState.value = giveaways
        }
    }

    private fun fetchFilteredGiveaways() {
        scope.launch {
            val giveaways = giveAwayRepository.fetchGiveaways(platforms)
            giveawaysState.value = giveaways
        }
    }

    fun onFilterClicked(filter: Filter) {
        if (filter.enabled) {
            platforms.add(filter.value.value)
        } else {
            platforms.remove(filter.value.value)
        }
        if (platforms.size > 0) {
            fetchFilteredGiveaways()
        } else {
            fetchAll()
        }
    }
}