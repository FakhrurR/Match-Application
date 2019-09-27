package rijhalpc.jadwalsepakbola.view.detail

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_list_detail.*
import org.jetbrains.anko.toast
import rijhalpc.jadwalsepakbola.api.DBSport
import rijhalpc.jadwalsepakbola.api.DBSportRest
import rijhalpc.jadwalsepakbola.MatchFavorite
import rijhalpc.jadwalsepakbola.model.club.Clubs
import rijhalpc.jadwalsepakbola.model.match.ShowMatch
import rijhalpc.jadwalsepakbola.model.match.ShowMatchPresenter
import rijhalpc.jadwalsepakbola.model.repository.RepoPresenter
import rijhalpc.jadwalsepakbola.R
import rijhalpc.jadwalsepakbola.R.menu.menu_detail
import rijhalpc.jadwalsepakbola.utils.invisible
import rijhalpc.jadwalsepakbola.utils.visible

@SuppressLint("Registered")
class ListDetail : AppCompatActivity(), ViewDetail.View {

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var showMatch: ShowMatch


    override fun hideLoading() {
        progress_bar_detail.invisible()
    }

    override fun showLoading() {
        progress_bar_detail.visible()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun displayTeamBadgeHome(team: Clubs) {
        Picasso.get()
            .load(team.strTeamBadge)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(img_home)
    }

    override fun displayTeamBadgeAway(team: Clubs) {
        Picasso.get()
            .load(team.strTeamBadge)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(img_away)
    }

    private lateinit var jPresenterDetail: PresenterDetail
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_detail)
        val serve = DBSport.getClient().create(DBSportRest::class.java)
        val repo = RepoPresenter(applicationContext)
        val req = ShowMatchPresenter(serve)
        jPresenterDetail = PresenterDetail(this, req, repo)

        initData(showMatch)
        showMatch = intent.getParcelableExtra("match")
        jPresenterDetail.checkMatch(showMatch.idEvent.toString())
        jPresenterDetail.getTeamsBadgeHome(showMatch.idHomeTeam)
        jPresenterDetail.getTeamsBadgeAway(showMatch.idAwayTeam)


        supportActionBar?.title = getString(R.string.strmatch)
        teamname.text = showMatch.strEvent
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initData(matchEvent: ShowMatch) {
        if (matchEvent.intHomeScore == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                match_date.setTextColor(applicationContext.getColor(R.color.colorAccent))
            }
        }

        match_date.text = matchEvent.dateEvent
        home_score_match.text = matchEvent.intHomeScore
        away_score_match.text = matchEvent.intAwayScore

        home_shots.text = matchEvent.intHomeShots
        away_shot.text = matchEvent.intAwayShots

        home_goals.text = matchEvent.strHomeGoalDetails
        away_goals.text = matchEvent.strAwayGoalDetails

        home_goalkeeper.text = matchEvent.strHomeLineupGoalkeeper
        away_goalkeeper.text = matchEvent.strAwayLineupGoalkeeper

        home_defense.text = matchEvent.strHomeLineupDefense
        away_defense.text = matchEvent.strAwayLineupDefense

        home_midfield.text = matchEvent.strHomeLineupMidfield
        away_midfield.text = matchEvent.strAwayLineupMidfield

        home_forward.text = matchEvent.strHomeLineupForward
        away_forward.text = matchEvent.strAwayLineupForward

        home_substitutes.text = matchEvent.strHomeLineupSubstitutes
        away_substitutes.text = matchEvent.strAwayLineupSubstitutes

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(menu_detail, menu)
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
                    jPresenterDetail.insertMatch(
                        showMatch.idEvent.toString(), showMatch.idHomeTeam, showMatch.idAwayTeam
                    )
                    toast("Added match to favorite")
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
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.fav1)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.fav)
    }

    override fun setFavoriteState(favList: List<MatchFavorite>) {
        if (!favList.isEmpty()) isFavorite = true
    }
}
