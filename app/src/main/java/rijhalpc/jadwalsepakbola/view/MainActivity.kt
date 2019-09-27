package rijhalpc.jadwalsepakbola.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import rijhalpc.jadwalsepakbola.R
import rijhalpc.jadwalsepakbola.R.id.*
import rijhalpc.jadwalsepakbola.model.match.ShowMatch
import rijhalpc.jadwalsepakbola.view.detail.ListDetail
import rijhalpc.jadwalsepakbola.view.favorite.FavoriteMatch
import rijhalpc.jadwalsepakbola.view.previous.Previous
import rijhalpc.jadwalsepakbola.view.previous.Previous2
import rijhalpc.jadwalsepakbola.view.upcoming.Upcoming


class MainActivity : AppCompatActivity(),Previous2.OnFragmentInteractionListener{
    override fun onFragmentInteraction(item: ShowMatch) {
        startActivity<ListDetail>(
            "id_event" to item.idEvent
            ,   "date_event" to item.dateEvent
            ,   "home_team" to item.strHomeTeam
            ,   "home_score" to item.intHomeScore
            ,   "away_team" to item.strAwayTeam
            ,   "away_score" to item.intAwayScore )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                previous -> {
                    toast(R.string.previous)
                    val fragment = Previous2.newInstance()
                    openMatchFragment(fragment)
                }
                upcoming -> {
                    loadUpcoming(savedInstanceState)
                }
                favorite -> {
                    loadFavoritesMatch(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.previous
    }

    private fun openMatchFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    private fun loadFavoritesMatch(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, FavoriteMatch(), FavoriteMatch::class.java.simpleName)
                .commit()
        }
    }

    private fun loadPrevious(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, Previous2(), Previous::class.java.simpleName)
                .commit()
        }
    }

    private fun loadUpcoming(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, Upcoming(), Upcoming::class.java.simpleName)
                .commit()
        }
    }


}
