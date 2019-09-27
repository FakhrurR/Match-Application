package rijhalpc.jadwalsepakbola.view.previous

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_previous.*
import rijhalpc.jadwalsepakbola.R
import rijhalpc.jadwalsepakbola.adapter.PrevMatchAdapter
import rijhalpc.jadwalsepakbola.api.DbSportRepo
import rijhalpc.jadwalsepakbola.model.match.ShowMatch
import rijhalpc.jadwalsepakbola.utils.invisible
import rijhalpc.jadwalsepakbola.utils.visible

class Previous2 : Fragment(), PreviousView2 {

    private var matchLists: MutableList<ShowMatch> = mutableListOf()
    private var listener : OnFragmentInteractionListener? = null

    override fun hideLoading() {
       mainProgressBarP.invisible()
    }

    override fun showLoading() {
        mainProgressBarP.visible()
    }

    override fun displayFootballMatch(matchList: List<ShowMatch>) {
        matchLists.clear()
        matchLists.addAll(matchList)
        val rv = view!!.findViewById<RecyclerView>(R.id.rvFootballP)
        rv.layoutManager = LinearLayoutManager(context)
        adapter = PrevMatchAdapter(matchLists, listener)
        rv.adapter = adapter


    }


    override fun getFootballData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var previousPresenter2: PreviousPresenter2
    private lateinit var adapter: PrevMatchAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val apiReq  = DbSportRepo()
        val gson    = Gson()
        previousPresenter2 = PreviousPresenter2(this, apiReq, gson )
        previousPresenter2.getMatchPrevData("4332")
        return inflater.inflate(R.layout.fragment_previous, container, false)
    }

        override fun onAttach(context: Context) {
            super.onAttach(context)
            if (context is OnFragmentInteractionListener) {
                listener = context
            } else {
                throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
            }
        }

        override fun onDetach() {
            super.onDetach()
            listener = null
        }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(item: ShowMatch)
    }

    companion object {

        @JvmStatic
        fun newInstance() = Previous2()
    }
}