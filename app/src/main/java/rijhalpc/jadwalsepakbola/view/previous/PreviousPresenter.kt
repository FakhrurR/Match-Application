package rijhalpc.jadwalsepakbola.view.previous

import io.reactivex.disposables.CompositeDisposable
import rijhalpc.jadwalsepakbola.model.match.ShowMatchPresenter
import rijhalpc.jadwalsepakbola.utils.ScheduleApp

class PreviousPresenter(
    private val jView: PreviousView.View, private val showMatchPresenter: ShowMatchPresenter,
    private val appScheduleApp: ScheduleApp
) : PreviousView.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getFootballData() {
        jView.showLoading()
        compositeDisposable.add(showMatchPresenter.getFootballMatch("4332")
            .observeOn(appScheduleApp.ui())
            .subscribeOn(appScheduleApp.io())
            .subscribe {
                jView.displayFootballMatch(it.events)
                jView.hideLoading()
            })
    }
}