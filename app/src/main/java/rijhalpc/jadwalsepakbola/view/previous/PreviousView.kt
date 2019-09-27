package rijhalpc.jadwalsepakbola.view.previous

import rijhalpc.jadwalsepakbola.model.match.ShowMatch

interface PreviousView {

    interface View {
        fun hideLoading()
        fun showLoading()
        fun displayFootballMatch(matchList: List<ShowMatch>)

    }

    interface Presenter {
        fun getFootballData()

    }
}