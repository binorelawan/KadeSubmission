package relawan.kade2.view.favorite.team


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import relawan.kade2.database.database
import relawan.kade2.databinding.FragmentFavoriteTeamBinding
import relawan.kade2.model.Teams

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTeamFragment : Fragment() {

    lateinit var favoriteTeamViewModel: FavoriteTeamViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {


        val binding = FragmentFavoriteTeamBinding.inflate(inflater)

        binding.lifecycleOwner = this

        favoriteTeamViewModel = ViewModelProvider(this).get(FavoriteTeamViewModel::class.java)

        val adapter = FavoriteTeamAdapter(FavoriteTeamAdapter.OnClickListener {
            // navigate to detailTeamFragment with argument
            val action = FavoriteTeamFragmentDirections.actionFavoriteTeamFragmentToDetailTeamFragment(null, null, it.idTeam)
            findNavController().navigate(action)
            Toast.makeText(context, it.idLeague, Toast.LENGTH_LONG).show()

        })

        binding.favoriteRecycler.adapter = adapter

        favoriteTeamViewModel.favoriteTeam.observe(viewLifecycleOwner, Observer {
            it.let {
                adapter.data = it
            }
        })


        return binding.root
    }

}
