package rijhalpc.jadwalsepakbola.utils

import io.reactivex.Scheduler
import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.CoroutineContext

interface ScheduleApp {

    fun ui(): Scheduler
    fun io(): Scheduler


}

open class CoroutinesContextProvider {
    open val main: CoroutineContext by lazy { UI }
}