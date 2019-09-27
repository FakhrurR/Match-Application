package rijhalpc.jadwalsepakbola.view.detail

import rijhalpc.jadwalsepakbola.MatchFavorite
import rijhalpc.jadwalsepakbola.model.club.Clubs
import rijhalpc.jadwalsepakbola.model.match.ShowMatch

interface ViewDetail2 {
    fun hideLoading()
    fun showLoading()
    fun displayTeamBadge(team: List<Clubs>)
    fun displayFootballMatch(matchList: List<ShowMatch>)
}