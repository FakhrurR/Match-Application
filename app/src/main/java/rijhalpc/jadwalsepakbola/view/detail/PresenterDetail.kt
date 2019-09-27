package rijhalpc.jadwalsepakbola.view.detail

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rijhalpc.jadwalsepakbola.model.match.ShowMatchPresenter
import rijhalpc.jadwalsepakbola.model.repository.RepoPresenter

class PresenterDetail(
    private val jViewDetail: ViewDetail.View, private val mshowMatchPresenter: ShowMatchPresenter,
    private val repoPresenter: RepoPresenter
) : ViewDetail.Presenter {

    private val compositeDisposable = CompositeDisposable()


    override fun getTeamsBadgeHome(id: String) {
        jViewDetail.showLoading()
        compositeDisposable.add(mshowMatchPresenter.getTeams(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                jViewDetail.displayTeamBadgeHome(it.teams[0])
                jViewDetail.hideLoading()
            })
    }


    override fun getTeamsBadgeAway(id: String) {
        jViewDetail.showLoading()
        compositeDisposable.add(mshowMatchPresenter.getTeams(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                jViewDetail.displayTeamBadgeAway(it.teams[0])
                jViewDetail.hideLoading()
            })
    }

    override fun deleteMatch(id: String) {
        repoPresenter.deleteData(id)
    }

    override fun checkMatch(id: String) {
        jViewDetail.setFavoriteState(repoPresenter.checkFavorite(id))
    }

    override fun insertMatch(eventId: String, homeId: String, awayId: String) {
        repoPresenter.insertData(eventId, homeId, awayId)
    }
}