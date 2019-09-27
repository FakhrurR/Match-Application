package rijhalpc.jadwalsepakbola.view.favorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_favorite_match.*
import org.jetbrains.anko.support.v4.onRefresh
import rijhalpc.jadwalsepakbola.adapter.AdapterClubs
import rijhalpc.jadwalsepakbola.api.DBSport
import rijhalpc.jadwalsepakbola.api.DBSportRest
import rijhalpc.jadwalsepakbola.model.match.ShowMatch
import rijhalpc.jadwalsepakbola.model.match.ShowMatchPresenter
import rijhalpc.jadwalsepakbola.model.repository.RepoPresenter
import rijhalpc.jadwalsepakbola.R
import rijhalpc.jadwalsepakbola.utils.AppView
import rijhalpc.jadwalsepakbola.utils.invisible
import rijhalpc.jadwalsepakbola.utils.visible


class FavoriteMatch : Fragment(), FavoriteView.View {
    override fun hideSwipeRefresh() {
        swipe.isRefreshing = false
        mainProgressBarFav.invisible()
        rvFootballFav.visible()
    }

    private var matchLists: MutableList<ShowMatch> = mutableListOf()
    private lateinit var jPresenter: FavoritePresenter

    override fun hideLoading() {
        mainProgressBarFav.invisible()
        rvFootballFav.visible()
    }

    override fun showLoading() {
        mainProgressBarFav.visible()
        rvFootballFav.invisible()
    }

    override fun displayFootballMatch(matchList: List<ShowMatch>) {
        matchLists.clear()
        matchLists.addAll(matchList)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvFootballFav.layoutManager = layoutManager
        rvFootballFav.adapter = AdapterClubs(matchList, context)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val serve = DBSport.getClient().create(DBSportRest::class.java)
        val req = ShowMatchPresenter(serve)
        val repo = RepoPresenter(context!!)
        val appView = AppView()
        jPresenter = FavoritePresenter(this, req, repo, appView)
        jPresenter.getFootballMatchData()
        swipe.onRefresh {
            jPresenter.getFootballMatchData()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_match, container, false)
    }
}
