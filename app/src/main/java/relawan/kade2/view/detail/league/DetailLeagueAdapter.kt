package relawan.kade2.view.detail.league

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import relawan.kade2.databinding.ListDetailLeagueBinding
import relawan.kade2.model.DetailLeague

class DetailLeagueAdapter : RecyclerView.Adapter<DetailLeagueAdapter.ViewHolder>() {

    var data = listOf<DetailLeague>()
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

    class ViewHolder(private val binding: ListDetailLeagueBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DetailLeague) {
            binding.titleDetail.text = item.strLeague
            binding.countryDetail.text = item.strCountry
            binding.trophyDetail.let { view ->
                Glide.with(view.context).load(item.strTrophy).into(binding.trophyDetail)
            }
            binding.logoDetail.let {view ->
                Glide.with(view.context).load(item.strLogo).into(binding.logoDetail)
            }

            binding.executePendingBindings()
        }

        companion object {
            // binding viewHolder
            fun from (parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)

                val binding = ListDetailLeagueBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}