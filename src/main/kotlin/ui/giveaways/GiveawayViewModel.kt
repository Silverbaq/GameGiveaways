package ui.giveaways

import androidx.compose.runtime.mutableStateOf
import data.network.response.GiveAwayItem
import data.repositories.GiveAwayRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import utils.GiveawayFilters

class GiveawayViewModel(private val giveAwayRepository: GiveAwayRepository) {
    private val scope = CoroutineScope(SupervisorJob())
    private val plaforms = mutableListOf<String>()

    val giveawaysState = mutableStateOf<List<GiveAwayItem>>(emptyList())
    var filterPCState = mutableStateOf(false)

    init {
        fetchAll()
    }

    fun fetchAll() {
        scope.launch {
            val giveaways = giveAwayRepository.fetchAllGiveaways()
            giveawaysState.value = giveaways
        }
    }

    fun fetchFilteredGiveaways() {
        scope.launch {
            val giveaways = giveAwayRepository.fetchGiveaways(plaforms)
            giveawaysState.value = giveaways
        }
    }

    fun onFilterPCClicked() {
        filterPCState.value = !filterPCState.value
        if (filterPCState.value) {
            plaforms.add(GiveawayFilters.PC.name)
        } else {
            plaforms.remove(GiveawayFilters.PC.name)
        }
        if (plaforms.size > 0) {
            fetchFilteredGiveaways()
        } else {
            fetchAll()
        }
    }
}