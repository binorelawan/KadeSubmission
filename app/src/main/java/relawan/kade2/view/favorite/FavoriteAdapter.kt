package relawan.kade2.view.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import relawan.kade2.R
import relawan.kade2.database.Favorite
import relawan.kade2.databinding.ListFavoriteBinding
import relawan.kade2.utils.DateTime

class FavoriteAdapter(private val favorite: List<Favorite>) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return favorite.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = favorite[position]

        holder.bind(item)
    }

    class ViewHolder(val binding: ListFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {

        private val strip = itemView.resources.getString(R.string.strip)

        fun bind(item : Favorite){
            val date = DateTime.getDate("${item.dateEvent} ${item.timeEvent}")
            binding.dateFixture.text = date.substringBeforeLast(";")
            binding.teamHome.text = item.homeTeam
            binding.teamAway.text = item.awayTeam
            binding.scoreHome.text = item.homeScore ?: strip
            binding.scoreAway.text = item.awayScore ?: strip

            binding.executePendingBindings()


        }

        companion object {
            // binding viewHolder
            fun from (parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)

                val binding = ListFavoriteBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }
}