package ui.giveaways

import androidx.compose.runtime.mutableStateOf
import data.network.response.GiveAwayItem
import data.repositories.GiveAwayRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GiveawayViewModel(private val giveAwayRepository: GiveAwayRepository) {
    val giveawaysState = mutableStateOf<List<GiveAwayItem>>(emptyList())

    init {
        fetchAll()
    }

    fun fetchAll() {
        GlobalScope.launch {
            val giveaways = giveAwayRepository.fetchAllGiveaways()
            giveawaysState.value = giveaways
        }
    }
}