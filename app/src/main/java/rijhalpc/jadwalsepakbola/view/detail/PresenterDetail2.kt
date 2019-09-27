package rijhalpc.jadwalsepakbola.view.detail

import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import rijhalpc.jadwalsepakbola.api.DBSportApi
import rijhalpc.jadwalsepakbola.api.DbSportRepo
import rijhalpc.jadwalsepakbola.model.club.ClubsResponse
import rijhalpc.jadwalsepakbola.model.match.ShowMatchResponse
import rijhalpc.jadwalsepakbola.utils.CoroutinesContextProvider

class PresenterDetail2 ( public val mViewDetail2: ViewDetail2,
                         public val mRepo: DbSportRepo,
                         public val gson: Gson
                         ,private val context: CoroutinesContextProvider = CoroutinesContextProvider()) {

    fun getTeamBadge( team: String?, teamType: String? ){

        doAsync {
            val dataTeam = gson.fromJson(mRepo
                .doRequest(DBSportApi.getBadge(team))
                , ClubsResponse::class.java )

            uiThread {
                if(teamType == "Away")
                    mViewDetail2.displayTeamBadge(dataTeam.teams)
                else
                    mViewDetail2.displayTeamBadge(dataTeam.teams)

            }
        }
    }

    fun getMatchEventDetail(event: String?){

        doAsync {
            val dataDetail = gson.fromJson( mRepo.doRequest(
                DBSportApi.getDetailMatch(event))
                , ShowMatchResponse::class.java )

            uiThread {
                mViewDetail2.displayFootballMatch(dataDetail.events)
            }
        }
    }

}