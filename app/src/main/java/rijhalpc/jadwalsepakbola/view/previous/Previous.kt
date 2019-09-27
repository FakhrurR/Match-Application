package rijhalpc.jadwalsepakbola.view.previous


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_previous.*
import rijhalpc.jadwalsepakbola.adapter.AdapterClubs
import rijhalpc.jadwalsepakbola.api.DBSport
import rijhalpc.jadwalsepakbola.api.DBSportRest
import rijhalpc.jadwalsepakbola.model.match.ShowMatch
import rijhalpc.jadwalsepakbola.model.match.ShowMatchPresenter
import rijhalpc.jadwalsepakbola.R
import rijhalpc.jadwalsepakbola.utils.AppView
import rijhalpc.jadwalsepakbola.utils.invisible
import rijhalpc.jadwalsepakbola.utils.visible


class Previous : Fragment(), PreviousView.View {

    override fun hideLoading() {
        mainProgressBarP.invisible()
        rvFootballP.visible()
    }

    override fun showLoading() {
        mainProgressBarP.visible()
        rvFootballP.invisible()
    }

    override fun displayFootballMatch(matchList: List<ShowMatch>) {
        matchLists.clear()
        matchLists.addAll(matchList)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvFootballP.layoutManager = layoutManager
        rvFootballP.adapter = AdapterClubs(matchList, context)
    }

    private lateinit var jPresenter: PreviousPresenter

    private var matchLists: MutableList<ShowMatch> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val serve = DBSport.getClient().create(DBSportRest::class.java)
        val req = ShowMatchPresenter(serve)
        val appApp = AppView()
        jPresenter = PreviousPresenter(this, req, appApp)
        jPresenter.getFootballData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_previous, container, false)
    }

}
