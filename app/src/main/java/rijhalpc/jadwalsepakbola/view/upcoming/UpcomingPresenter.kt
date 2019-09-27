package rijhalpc.jadwalsepakbola.view.upcoming

import io.reactivex.disposables.CompositeDisposable
import rijhalpc.jadwalsepakbola.model.match.ShowMatchPresenter
import rijhalpc.jadwalsepakbola.utils.ScheduleApp

class UpcomingPresenter(
    private val jView: UpcomingView.View, private val showMatchPresenter: ShowMatchPresenter,
    private val appApp: ScheduleApp
) : UpcomingView.Presenter {

    private val compositeDisposable = CompositeDisposable()
    override fun getUpcomingData() {
        jView.showLoading()
        compositeDisposable.add(showMatchPresenter.getUpcomingMatch("4332")
            .observeOn(appApp.ui())
            .subscribeOn(appApp.io())
            .subscribe {
                jView.displayFootballMatch(it.events)
                jView.hideLoading()
            })
    }
}