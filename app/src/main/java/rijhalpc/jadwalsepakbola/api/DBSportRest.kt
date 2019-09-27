package rijhalpc.jadwalsepakbola.api

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query
import rijhalpc.jadwalsepakbola.model.club.ClubsResponse
import rijhalpc.jadwalsepakbola.model.match.ShowMatchResponse

interface DBSportRest {

    @GET("eventspastleague.php")
    fun getLastmatch(@Query("id") id: String): Flowable<ShowMatchResponse>

    @GET("eventsnextleague.php")
    fun getUpcomingMatch(@Query("id") id: String): Flowable<ShowMatchResponse>

    @GET("lookupteam.php")
    fun getTeam(@Query("id") id: String): Flowable<ClubsResponse>

    @GET("lookupevent.php")
    fun getEventById(@Query("id") id: String): Flowable<ShowMatchResponse>
}