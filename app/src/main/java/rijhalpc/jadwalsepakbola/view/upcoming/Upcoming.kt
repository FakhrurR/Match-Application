package rijhalpc.jadwalsepakbola.view.upcoming

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_upcoming.*
import rijhalpc.jadwalsepakbola.adapter.AdapterClubs
import rijhalpc.jadwalsepakbola.api.DBSport
import rijhalpc.jadwalsepakbola.api.DBSportRest
import rijhalpc.jadwalsepakbola.model.match.ShowMatch
import rijhalpc.jadwalsepakbola.model.match.ShowMatchPresenter
import rijhalpc.jadwalsepakbola.R
import rijhalpc.jadwalsepakbola.utils.AppView
import rijhalpc.jadwalsepakbola.utils.invisible
import rijhalpc.jadwalsepakbola.utils.visible

class Upcoming : Fragment(),UpcomingView.View {

    override fun hideLoading() {
        mainProgressBarU.invisible()
        rvFootballU.visible()
    }

    override fun showLoading() {
        mainProgressBarU.visible()
        rvFootballU.invisible()
    }

    override fun displayFootballMatch(matchList: List<ShowMatch>) {
        matchLists.clear()
        matchLists.addAll(matchList)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvFootballU.layoutManager = layoutManager
        rvFootballU.adapter = AdapterClubs(matchList,context)
    }


    private lateinit var jPresenter: UpcomingPresenter

    private var matchLists : MutableList<ShowMatch> = mutableListOf()
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        super.onCreate(savedInstanceState)
        val serve = DBSport.getClient().create(DBSportRest::class.java)
        val req = ShowMatchPresenter(serve)
        val appApp = AppView()
        jPresenter = UpcomingPresenter(this, req, appApp)
        jPresenter.getUpcomingData()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_upcoming, container, false)
    }
}
