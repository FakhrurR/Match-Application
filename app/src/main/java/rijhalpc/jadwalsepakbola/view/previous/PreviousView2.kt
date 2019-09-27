package rijhalpc.jadwalsepakbola.view.previous

import rijhalpc.jadwalsepakbola.model.match.ShowMatch

interface PreviousView2 {

    fun hideLoading()
    fun showLoading()
    fun displayFootballMatch(matchList: List<ShowMatch>)
    fun getFootballData()
}