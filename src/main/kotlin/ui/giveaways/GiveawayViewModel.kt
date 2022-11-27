package ui.giveaways

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import data.network.response.GiveAwayItem
import data.repositories.GiveAwayRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ui.giveaways.filter.Filter
import ui.giveaways.filter.FilterProvider

class GiveawayViewModel(
    private val giveAwayRepository: GiveAwayRepository,
    private val filterProvider: FilterProvider,
    private val scope: CoroutineScope,
) {
    private val platforms = mutableListOf<String>()
    var isLoading by mutableStateOf(false)

    val filters: Map<String, List<Filter>>
        get() = filterProvider.filters

    var giveawaysState by mutableStateOf<List<GiveAwayItem>>(emptyList())

    private fun fetchAll() {
        scope.launch {
            isLoading = true
            val giveaways = giveAwayRepository.fetchAllGiveaways()
            giveawaysState = giveaways
            isLoading = false
        }
    }

    private fun fetchFilteredGiveaways() {
        scope.launch {
            isLoading = true
            val giveaways = giveAwayRepository.fetchGiveaways(platforms)
            giveawaysState = giveaways
            isLoading = false
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

    fun onViewReady() {
        fetchAll()
    }
}
