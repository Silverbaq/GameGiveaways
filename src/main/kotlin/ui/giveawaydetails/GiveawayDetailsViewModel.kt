package ui.giveawaydetails

import androidx.compose.runtime.mutableStateOf
import data.network.response.GiveAwayItem
import data.repositories.GiveAwayRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class GiveawayDetailsViewModel(
    private val giveAwayRepository: GiveAwayRepository,
    private val customScope: CoroutineScope,
    ) {
    val giveawayDetails = mutableStateOf(GiveAwayItem.EMPTY)

    fun onViewReady(id: Int) {
        customScope.launch{
            giveawayDetails.value = giveAwayRepository.fetchGiveawayDetails(id)
        }
    }
}