package relawan.kade2.view.favorite.team


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

    private var favorites: MutableList<Teams> = mutableListOf()
    private lateinit var mAdapter: FavoriteTeamAdapter

    private lateinit var favoriteRecycler: RecyclerView
    private lateinit var favoriteSwipe: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {


        val binding = FragmentFavoriteTeamBinding.inflate(inflater)

        binding.lifecycleOwner = this

        favoriteRecycler = binding.favoriteRecycler
        favoriteSwipe = binding.favoriteSwipe

        mAdapter = FavoriteTeamAdapter(favorites, FavoriteTeamAdapter.OnClickListener {

            val action = FavoriteTeamFragmentDirections.actionFavoriteTeamFragmentToDetailTeamFragment(it, null)
            findNavController().navigate(action)
        })

        favoriteRecycler.adapter = mAdapter

        showFavorite()

        favoriteSwipe.setOnRefreshListener {
            showFavorite()
        }


        return binding.root
    }

    private fun showFavorite() {
        favorites.clear()
        context?.database?.use {
            favoriteSwipe.isRefreshing = false
            val result = select(Teams.TABLE_FAVORITE_TEAM)
            val favorite = result.parseList(classParser<Teams>())
            favorites.addAll(favorite)
            mAdapter.notifyDataSetChanged()
        }
    }
}
