package rijhalpc.jadwalsepakbola.view.detail

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteConstraintException
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_list_detail.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.toast
import rijhalpc.jadwalsepakbola.MatchFavorite
import rijhalpc.jadwalsepakbola.R
import rijhalpc.jadwalsepakbola.api.DbSportRepo
import rijhalpc.jadwalsepakbola.database
import rijhalpc.jadwalsepakbola.model.club.Clubs
import rijhalpc.jadwalsepakbola.model.match.ShowMatch
import rijhalpc.jadwalsepakbola.utils.invisible
import rijhalpc.jadwalsepakbola.utils.visible

@SuppressLint("Registered")
class ListDetail2 : AppCompatActivity(),ViewDetail2 {
    override fun hideLoading() {
        progress_bar_detail.invisible()
    }

    override fun showLoading() {
        progress_bar_detail.visible()
    }

    override fun displayTeamBadge(team: List<Clubs>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayFootballMatch(matchList: List<ShowMatch>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var jPresenterDetail2: PresenterDetail2

    private lateinit var showMatch: ShowMatch
    private lateinit var id_event : String
    private var home_team : String? = null
    private var away_team : String? = null
    private var home_score : String? = null
    private var away_score : String? = null
    private var date_event : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiReq = DbSportRepo()
        val gson = Gson()
        jPresenterDetail2 = PresenterDetail2(this, apiReq, gson)
        jPresenterDetail2.getMatchEventDetail(id_event)

        val intent = intent

        id_event    = intent.getStringExtra("id_event")
        home_team   = intent.getStringExtra("home_team")
        home_score  = intent.getStringExtra("home_score")
        away_team   = intent.getStringExtra("away_team" )
        away_score  = intent.getStringExtra("away_score")
        date_event  = intent.getStringExtra("date_event")

        if (showMatch.intHomeScore == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                match_date.setTextColor(applicationContext.getColor(R.color.colorAccent))
            }
        }

        match_date.text = showMatch.dateEvent
        home_score_match.text = showMatch.intHomeScore
        away_score_match.text = showMatch.intAwayScore

        home_shots.text = showMatch.intHomeShots
        away_shot.text = showMatch.intAwayShots

        home_goals.text = showMatch.strHomeGoalDetails
        away_goals.text = showMatch.strAwayGoalDetails

        home_goalkeeper.text = showMatch.strHomeLineupGoalkeeper
        away_goalkeeper.text = showMatch.strAwayLineupGoalkeeper

        home_defense.text = showMatch.strHomeLineupDefense
        away_defense.text = showMatch.strAwayLineupDefense

        home_midfield.text = showMatch.strHomeLineupMidfield
        away_midfield.text = showMatch.strAwayLineupMidfield

        home_forward.text = showMatch.strHomeLineupForward
        away_forward.text = showMatch.strAwayLineupForward

        home_substitutes.text = showMatch.strHomeLineupSubstitutes
        away_substitutes.text = showMatch.strAwayLineupSubstitutes
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (!isFavorite) {
                    try {
                        database.use {
                            insert(MatchFavorite.TABLE_FAVORITE,
                                MatchFavorite.
                        }
                        snackbar("Added to Your Favorite Match").show()
                    }catch (e: SQLiteConstraintException){
                        snackbar( e.localizedMessage).show()
                    }
                } else {
                    jPresenterDetail.deleteMatch(showMatch.idEvent.toString())
                    toast("Remove match from favorite")
                }
                isFavorite=!isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavorite() {

    }

    override fun setFavoriteState(favList: List<MatchFavorite>) {
        if (!favList.isEmpty()) isFavorite = true
    }
}

}