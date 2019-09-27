package rijhalpc.jadwalsepakbola.view.detail

import rijhalpc.jadwalsepakbola.MatchFavorite
import rijhalpc.jadwalsepakbola.model.club.Clubs

interface ViewDetail {

    interface View {
        fun hideLoading()
        fun showLoading()
        fun displayTeamBadgeHome(team: Clubs)
        fun displayTeamBadgeAway(team: Clubs)
        fun setFavoriteState(favList: List<MatchFavorite>)
    }

    interface Presenter {
        fun getTeamsBadgeAway(id: String)
        fun getTeamsBadgeHome(id: String)
        fun deleteMatch(id: String)
        fun insertMatch(eventId: String, homeId: String, awayId: String)
        fun checkMatch(id: String)

    }
}