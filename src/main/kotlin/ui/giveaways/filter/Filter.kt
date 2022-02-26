package ui.giveaways.filter

import utils.GiveawayFilters

data class Filter(
    val title: String,
    var enabled: Boolean,
    val value: GiveawayFilters
)