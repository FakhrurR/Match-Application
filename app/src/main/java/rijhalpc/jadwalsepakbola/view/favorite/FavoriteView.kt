package rijhalpc.jadwalsepakbola.view.favorite

import rijhalpc.jadwalsepakbola.model.match.ShowMatch

interface FavoriteView {

    interface View {
        fun hideLoading()
        fun showLoading()
        fun displayFootballMatch(matchList: List<ShowMatch>)
        fun hideSwipeRefresh()
    }

    interface Presenter {
        fun getFootballMatchData()

    }
}