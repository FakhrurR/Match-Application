package rijhalpc.jadwalsepakbola.view.upcoming

import rijhalpc.jadwalsepakbola.model.match.ShowMatch

interface UpcomingView {

    interface View {
        fun hideLoading()
        fun showLoading()
        fun displayFootballMatch(matchList: List<ShowMatch>)

    }

    interface Presenter {
        fun getUpcomingData()

    }
}