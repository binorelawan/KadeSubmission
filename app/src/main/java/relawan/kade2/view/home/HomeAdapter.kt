package relawan.kade2.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import relawan.kade2.databinding.ListLeagueBinding
import relawan.kade2.model.DetailLeague
import relawan.kade2.model.League
import relawan.kade2.utils.LeagueLogo
import relawan.kade2.utils.LogoLeagueCallback

class HomeAdapter(private val onClickListener: OnClickListener) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    var data = listOf<League>()
        get() {
            // find soccer only
            return field.filter {
                it.strSport == "Soccer"
            }.take(10)
        }
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
        holder.bind(item)
    }

    class ViewHolder(private val binding: ListLeagueBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: League) {
            binding.leaguesName.text = item.strLeague

            binding.leaguesLogo.let { view ->
                LeagueLogo.getLeagueLogo(item.idLeague.toString(), object : LogoLeagueCallback {
                    override fun onError() {

                    }

                    override fun onSuccess(detailLeague: DetailLeague) {
                       detailLeague.run {
                           Glide.with(view.context).load(strBadge).into(binding.leaguesLogo)
                       }
                    }

                })
            }
            binding.executePendingBindings()

        }

        companion object {
            // binding viewHolder
            fun from (parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)

                val binding = ListLeagueBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

    // click listener
    class OnClickListener(val clickListener: (league: League) -> Unit) {
        fun onClick(league: League) = clickListener(league)
    }

}