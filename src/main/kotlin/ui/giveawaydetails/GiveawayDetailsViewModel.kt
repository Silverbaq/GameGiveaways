package ui.giveawaydetails

import androidx.compose.runtime.mutableStateOf
import data.network.response.GiveAwayItem
import data.repositories.GiveAwayRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GiveawayDetailsViewModel(private val giveAwayRepository: GiveAwayRepository) {
    val giveawayDetails = mutableStateOf(GiveAwayItem.EMPTY)

    fun onViewReady(id: Int) {
        GlobalScope.launch{
            giveawayDetails.value = giveAwayRepository.fetchGiveawayDetails(id)
        }
    }
}