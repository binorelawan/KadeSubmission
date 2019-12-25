package relawan.kade2.view.fixture.search.match

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import relawan.kade2.R
import relawan.kade2.databinding.ListSearchMatchBinding
import relawan.kade2.model.SearchMatch
import relawan.kade2.utils.DateTime

class SearchMatchAdapter(private val onClickListener: OnClickListener) : RecyclerView.Adapter<SearchMatchAdapter.ViewHolder>() {

    var data = listOf<SearchMatch>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(
            parent
        )
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

    class ViewHolder(val binding: ListSearchMatchBinding) : RecyclerView.ViewHolder(binding.root) {

        private val strip = itemView.resources.getString(R.string.strip)

        fun bind(item: SearchMatch) {
            val date = DateTime.getDate("${item.dateEvent} ${item.strTime}")
            binding.dateFixture.text = date.substringBeforeLast(";")
            binding.teamHome.text = item.strHomeTeam
            binding.teamAway.text = item.strAwayTeam
            binding.scoreHome.text = item.intHomeScore ?: strip
            binding.scoreAway.text = item.intAwayScore ?: strip

            binding.executePendingBindings()
        }

        companion object {
            // binding viewHolder
            fun from (parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)

                val binding = ListSearchMatchBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class OnClickListener(val clickListener: (searchMatch: SearchMatch) -> Unit) {
        fun onClick(searchMatch: SearchMatch) = clickListener(searchMatch)
    }
}