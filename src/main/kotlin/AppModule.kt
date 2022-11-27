
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module

val appModule = module {
    single { CoroutineScope(SupervisorJob()) }
}
