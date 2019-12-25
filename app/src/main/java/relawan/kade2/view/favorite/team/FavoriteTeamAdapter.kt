package relawan.kade2.view.favorite.team

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import relawan.kade2.databinding.ListFavoriteTeamBinding
import relawan.kade2.model.Teams

class FavoriteTeamAdapter(private val favorite: List<Teams>, private val onClickListener: OnClickListener) : RecyclerView.Adapter<FavoriteTeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return favorite.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = favorite[position]
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
        holder.bind(item)
    }

    class ViewHolder(val binding: ListFavoriteTeamBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Teams) {
            binding.teamsName.text = item.strTeam
            binding.teamsLogo.let { view ->
                Glide.with(view.context).load(item.strTeamBadge).into(binding.teamsLogo)
            }
        }

        companion object {
            // binding viewHolder
            fun from (parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)

                val binding = ListFavoriteTeamBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    // click listener
    class OnClickListener(val clickListener: (teams: Teams) -> Unit) {
        fun onClick(teams: Teams) = clickListener(teams)
    }
}