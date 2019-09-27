package rijhalpc.jadwalsepakbola.adapter

import android.content.Context
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_list.view.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import rijhalpc.jadwalsepakbola.model.match.ShowMatch
import rijhalpc.jadwalsepakbola.R
import rijhalpc.jadwalsepakbola.view.detail.ListDetail

class AdapterClubs(private val eventList: List<ShowMatch>, val context: Context?) :
    RecyclerView.Adapter<AdapterClubs.ClubViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubViewHolder {
        return ClubViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))
    }

    override fun getItemCount(): Int = eventList.size


    override fun onBindViewHolder(holder: ClubViewHolder, position: Int) {
        holder.bind(eventList[position])
    }

    inner class ClubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateScheduleTv: TextView = itemView.find(R.id.dateScheduleTv)
        val homeNameTv: TextView = itemView.find(R.id.homeNameTv)
        val homeScoreTv: TextView = itemView.find(R.id.homeScoreTv)
        val awayNameTv: TextView = itemView.find(R.id.awayNameTv)
        val awayScoreTv: TextView = itemView.find(R.id.awayScoreTv)

        fun bind(event: ShowMatch) {
            if (event.intHomeScore == null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (context != null) {
                        itemView.dateScheduleTv.setTextColor(context.getColor(R.color.colorAccent))
                    }
                }
            }
            dateScheduleTv.text = event.dateEvent
            homeNameTv.text = event.strHomeTeam
            homeScoreTv.text = event.intHomeScore
            awayNameTv.text = event.strAwayTeam
            awayScoreTv.text = event.intAwayScore

            itemView.setOnClickListener {
                itemView.context.startActivity<ListDetail>("match" to event)
            }

        }
    }
}