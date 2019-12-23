package relawan.kade2.view.fixture.teams

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import relawan.kade2.databinding.ListTeamBinding
import relawan.kade2.model.Teams

class TeamsAdapter(private val onClickListener: OnClickListener) : RecyclerView.Adapter<TeamsAdapter.ViewHolder>() {

    var data = listOf<Teams>()
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
        holder.itemView.setOnClickListener { view ->
            onClickListener.onClick(item)
        }
        holder.bind(item)
    }

    class ViewHolder(val binding: ListTeamBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Teams) {
            binding.teamsName.text = item.strTeam
            binding.teamsLogo.let { view ->
                Glide.with(view.context).load(item.strTeamBadge).into(binding.teamsLogo)
            }

            binding.executePendingBindings()
        }


        companion object {
            // binding viewHolder
            fun from (parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)

                val binding = ListTeamBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    // click listener
    class OnClickListener(val clickListener: (teams: Teams) -> Unit) {
        fun onClick(teams: Teams) = clickListener(teams)
    }
}