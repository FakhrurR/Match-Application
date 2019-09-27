package rijhalpc.jadwalsepakbola.utils

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppView : ScheduleApp {
    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
    override fun io() = Schedulers.io()
}
