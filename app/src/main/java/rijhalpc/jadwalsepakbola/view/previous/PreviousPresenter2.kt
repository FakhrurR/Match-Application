package rijhalpc.jadwalsepakbola.view.previous

import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import rijhalpc.jadwalsepakbola.api.DBSportApi
import rijhalpc.jadwalsepakbola.api.DbSportRepo
import rijhalpc.jadwalsepakbola.model.match.ShowMatchResponse
import rijhalpc.jadwalsepakbola.utils.CoroutinesContextProvider

class PreviousPresenter2(private val view: PreviousView2,
                         private val apiRepository: DbSportRepo,
                         private val gson : Gson,
                         private val context: CoroutinesContextProvider = CoroutinesContextProvider()) {

    fun getMatchPrevData(league: String?){
        Log.d("debug","league :" + league)
        doAsync {
            val dataMatch = gson.fromJson(apiRepository
                .doRequest(DBSportApi.getPrevMatch(league))
                ,ShowMatchResponse::class.java
            )
            Log.d("Debug", "data log: " + dataMatch)

            uiThread {
                view.displayFootballMatch(dataMatch.events)
            }

        }
    }

}