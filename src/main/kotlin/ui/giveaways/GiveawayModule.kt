package ui.giveaways

import org.koin.core.scope.get
import org.koin.dsl.module

val giveawayModule = module {
    single { GiveawayViewModel(get()) }
}