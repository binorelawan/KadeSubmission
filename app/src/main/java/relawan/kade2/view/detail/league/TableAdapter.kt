package relawan.kade2.view.detail.league

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import relawan.kade2.databinding.ListTableLeagueBinding
import relawan.kade2.model.Table

class TableAdapter : RecyclerView.Adapter<TableAdapter.ViewHolder>() {

    var data = listOf<Table>()

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

    class ViewHolder(private val binding: ListTableLeagueBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(item: Table) {
            binding.rank.text = (this.adapterPosition + 1).toString()
            binding.tableTeam.text = item.name
            binding.play.text = item.played.toString()
            binding.goalDiff.text = item.goalsdifference.toString()
            binding.point.text = item.total.toString()
        }

        companion object {
            // binding viewHolder
            fun from (parent: ViewGroup) : ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)

                val binding = ListTableLeagueBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }
}