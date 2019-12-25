package relawan.kade2.view.favorite.match


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import relawan.kade2.database.database
import relawan.kade2.databinding.FragmentFavoriteMatchBinding
import relawan.kade2.model.Match

/**
 * A simple [Fragment] subclass.
 */
class FavoriteMatchFragment : Fragment() {

    private var favorites: MutableList<Match> = mutableListOf()
    private lateinit var mAdapter: FavoriteMatchAdapter

    private lateinit var favoriteRecycler: RecyclerView
    private lateinit var favoriteSwipe: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {


        val binding = FragmentFavoriteMatchBinding.inflate(inflater)

        binding.lifecycleOwner = this

        favoriteRecycler = binding.favoriteRecycler
        favoriteSwipe = binding.favoriteSwipe

        mAdapter = FavoriteMatchAdapter(favorites, FavoriteMatchAdapter.OnClickListener {

            val action = FavoriteMatchFragmentDirections.actionFavoriteMatchFragmentToDetailMatchFragment(it, null)
            findNavController().navigate(action)

        })

        favoriteRecycler.layoutManager = LinearLayoutManager(context)
        favoriteRecycler.adapter = mAdapter

        showFavorite()

        favoriteSwipe.setOnRefreshListener {
            showFavorite()
        }

        return binding.root

    }


    private fun showFavorite(){
        favorites.clear()
        context?.database?.use {
            favoriteSwipe.isRefreshing = false
            val result = select(Match.TABLE_FAVORITE_MATCH)
            val favorite = result.parseList(classParser<Match>())
            favorites.addAll(favorite)
            mAdapter.notifyDataSetChanged()
        }
    }
}
