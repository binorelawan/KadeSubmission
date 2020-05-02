package relawan.kade2.view.detail.match

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import relawan.kade2.R
import relawan.kade2.databinding.ListDetailMatchBinding
import relawan.kade2.model.DetailMatch
import relawan.kade2.model.DetailTeam
import relawan.kade2.utils.DateTime
import relawan.kade2.utils.TeamItemCallback
import relawan.kade2.utils.TeamLogo

class DetailMatchAdapter : RecyclerView.Adapter<DetailMatchAdapter.ViewHolder>() {

    var data = listOf<DetailMatch>()
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

        holder.bind(item)
    }

    class ViewHolder(val binding: ListDetailMatchBinding) : RecyclerView.ViewHolder(binding.root){


        private val strip = itemView.resources.getString(R.string.strip)

    fun bind(item : DetailMatch) {

        // call date time to local time(indonesia)
        val date = DateTime.getDate("${item.dateEvent} ${item.strTime}")

        binding.dateFixture.text = date.substringBeforeLast(";")
        binding.timeFixture.text = date.substringAfterLast(";")

        binding.teamHome.text = item.strHomeTeam
        binding.teamAway.text = item.strAwayTeam

        binding.scoreHome.text = item.intHomeScore ?: strip
        binding.scoreAway.text = item.intAwayScore ?: strip

        // detail goal
        binding.detailGoalHome.text = if (item.strHomeGoalDetails == "" || item.strHomeGoalDetails == null) strip
                                        else item.strHomeGoalDetails!!.replace(";","\n")
        binding.detailGoalAway.text = if (item.strAwayGoalDetails == "" || item.strAwayGoalDetails == null) strip
                                        else item.strAwayGoalDetails!!.replace(";","\n")

        // detail yellow card
        binding.yellowCardHome.text = if(item.strHomeYellowCards == "" || item.strHomeYellowCards == null) strip
                                        else item.strHomeYellowCards!!.replace(";", "\n")
        binding.yellowCardAway.text = if (item.strAwayYellowCards == "" || item.strAwayYellowCards == null) strip
                                        else item.strAwayYellowCards!!.replace(";", "\n")
        // detail red card
        binding.redCardHome.text = if(item.strHomeRedCards == "" || item.strHomeRedCards == null) strip
                                        else item.strHomeRedCards!!.replace(";", "\n")
        binding.redCardAway.text = if(item.strAwayRedCards == "" || item.strAwayRedCards == null) strip
                                        else item.strAwayRedCards!!.replace(";", "\n")

        // show logo
        binding.logoHome.let {view ->
            TeamLogo.getTeamLogo(item.idHomeTeam.toString(), object : TeamItemCallback{
                override fun onError() {

                }

                override fun onSuccess(detailTeam: DetailTeam) {
                    detailTeam.run {
                        Glide.with(view.context).load(strTeamBadge).into(binding.logoHome)
                    }
                }

            })
        }

        binding.logoAway.let { view ->
            TeamLogo.getTeamLogo(item.idAwayTeam.toString(), object : TeamItemCallback{
                override fun onError() {

                }

                override fun onSuccess(detailTeam: DetailTeam) {
                    detailTeam.run {
                        Glide.with(view.context).load(strTeamBadge).into(binding.logoAway)
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

                val binding = ListDetailMatchBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}