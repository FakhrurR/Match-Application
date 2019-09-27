package rijhalpc.jadwalsepakbola.model.match

import io.reactivex.Flowable
import rijhalpc.jadwalsepakbola.api.DBSportRest
import rijhalpc.jadwalsepakbola.model.club.ClubsResponse

class ShowMatchPresenter(private val dbSportRest: DBSportRest) : ViewMatch {

    override fun getUpcomingMatch(id: String): Flowable<ShowMatchResponse> = dbSportRest.getUpcomingMatch(id)

    override fun getFootballMatch(id: String): Flowable<ShowMatchResponse> = dbSportRest.getLastmatch(id)

    override fun getTeams(id: String): Flowable<ClubsResponse> = dbSportRest.getTeam(id)

    fun getEventById(id: String): Flowable<ShowMatchResponse> = dbSportRest.getEventById(id)
}