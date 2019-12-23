package relawan.kade2.view.detail.team

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import relawan.kade2.R
import relawan.kade2.databinding.ListDetailTeamBinding
import relawan.kade2.model.DetailTeam

class DetailTeamAdapter : RecyclerView.Adapter<DetailTeamAdapter.ViewHolder>() {

    var data = listOf<DetailTeam>()
        get() {
            return field.take(1)
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

        holder.bind(item)
    }

    class ViewHolder(private val binding: ListDetailTeamBinding) : RecyclerView.ViewHolder(binding.root) {

        private val strip = itemView.resources.getString(R.string.strip)

        fun bind(item: DetailTeam) {
            binding.titleTeam.text = item.strTeam
            binding.yearTeam.text = item.intFormedYear ?: strip
            binding.countryTeam.text = item.strCountry ?: strip
            binding.stadiumTeam.text = item.strStadium ?: strip
            binding.descriptionTeam.text = item.strDescriptionEN
            binding.bannerTeam.let { view->
                Glide.with(view.context).load(item.strTeamBanner).into(binding.bannerTeam)
            }
            binding.badgeTeam.let { view ->
                Glide.with(view.context).load(item.strTeamBadge).into(binding.badgeTeam)
            }

            binding.executePendingBindings()
        }

        companion object {
            // binding viewHolder
            fun from (parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)

                val binding = ListDetailTeamBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}