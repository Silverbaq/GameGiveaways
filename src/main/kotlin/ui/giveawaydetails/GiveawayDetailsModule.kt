package ui.giveawaydetails

import org.koin.dsl.module

val giveawayDetailsModule = module {
    single { GiveawayDetailsViewModel(get(), get()) }
}
