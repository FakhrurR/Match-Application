package rijhalpc.jadwalsepakbola.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.find
import rijhalpc.jadwalsepakbola.R
import rijhalpc.jadwalsepakbola.model.match.ShowMatch
import rijhalpc.jadwalsepakbola.view.previous.Previous2

class PrevMatchAdapter (private val dataItems: MutableList<ShowMatch>,
                        private val listener: Previous2.OnFragmentInteractionListener?
)
: RecyclerView.Adapter<PrevMatchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return dataItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        holder.bindItem(dataItems[position], listener)
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)  {

        val dateScheduleTv: TextView = itemView.find(R.id.dateScheduleTv)
        val homeNameTv: TextView = itemView.find(R.id.homeNameTv)
        val homeScoreTv: TextView = itemView.find(R.id.homeScoreTv)
        val awayNameTv: TextView = itemView.find(R.id.awayNameTv)
        val awayScoreTv: TextView = itemView.find(R.id.awayScoreTv)

        fun bindItem(item: ShowMatch, listener: Previous2.OnFragmentInteractionListener?) {
            dateScheduleTv.text = item.dateEvent
            homeNameTv.text = item.strHomeTeam
            homeScoreTv.text = item.intHomeScore
            awayNameTv.text = item.strAwayTeam
            awayScoreTv.text = item.intAwayScore

            itemView.setOnClickListener {
                listener?.onFragmentInteraction(item)
            }
        }

    }

}
