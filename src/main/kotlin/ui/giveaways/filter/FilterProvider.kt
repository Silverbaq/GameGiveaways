package ui.giveaways.filter

import utils.GiveawayFilters

class FilterProvider {
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

    private val filterMap = mapOf(
        "Platforms" to platformFilters,
        "Providers" to providerFilter
    )

    val filters: Map<String, List<Filter>>
        get() = filterMap
}
