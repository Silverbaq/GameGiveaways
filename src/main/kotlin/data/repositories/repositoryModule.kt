package data.repositories

import data.network.RetrofitBuilder
import org.koin.dsl.module

val repositoryModule = module {
    single { RetrofitBuilder.gamerPowerService }
    single { GiveAwayRepository(get()) }
}