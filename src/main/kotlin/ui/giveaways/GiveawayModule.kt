package ui.giveaways

import org.koin.dsl.module
import ui.giveaways.filter.FilterProvider

val giveawayModule = module {
    single { FilterProvider() }
    single { GiveawayViewModel(get(), get()) }
}