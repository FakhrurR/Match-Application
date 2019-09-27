package rijhalpc.jadwalsepakbola.view.favorite

import io.reactivex.disposables.CompositeDisposable
import rijhalpc.jadwalsepakbola.model.match.ShowMatch
import rijhalpc.jadwalsepakbola.model.match.ShowMatchPresenter
import rijhalpc.jadwalsepakbola.model.repository.RepoPresenter
import rijhalpc.jadwalsepakbola.utils.AppView

class FavoritePresenter(private val jView: FavoriteView.View,
                        private val matchPresenter: ShowMatchPresenter,
                        private val repoPresenter: RepoPresenter,
                        private val appView: AppView) : FavoriteView.Presenter {

    override fun getFootballMatchData() {
        val compositeDisposable = CompositeDisposable()
        jView.showLoading()
        val favoriteList = repoPresenter.getMatchFromDb()

        val eventList: MutableList<ShowMatch> = mutableListOf()
        for (fav in favoriteList) {
            compositeDisposable.add(matchPresenter.getEventById(fav.idEvent)
                .observeOn(appView.ui())
                .subscribeOn(appView.io())
                .subscribe {
                    eventList.add(it.events[0])
                    jView.displayFootballMatch(eventList)
                    jView.hideLoading()
                    jView.hideSwipeRefresh()
                })
        }
        if (favoriteList.isEmpty()) {
            jView.hideLoading()
            jView.displayFootballMatch(eventList)
            jView.hideSwipeRefresh()
        }
    }
}