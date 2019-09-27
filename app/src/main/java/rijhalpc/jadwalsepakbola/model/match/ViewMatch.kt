package rijhalpc.jadwalsepakbola.model.match

import io.reactivex.Flowable
import rijhalpc.jadwalsepakbola.model.club.ClubsResponse

interface ViewMatch {

    fun getFootballMatch(id: String): Flowable<ShowMatchResponse>

    fun getTeams(id: String = "0"): Flowable<ClubsResponse>

    fun getUpcomingMatch(id: String): Flowable<ShowMatchResponse>
}