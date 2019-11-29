package relawan.kade2.view.favorite


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import relawan.kade2.database.Favorite
import relawan.kade2.database.database
import relawan.kade2.databinding.FragmentFavoriteBinding

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() {

    private var favorites: MutableList<Favorite> = mutableListOf()
    private lateinit var mAdapter: FavoriteAdapter

    lateinit var favoriteRecycler: RecyclerView
    lateinit var favoriteSwipe: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment


        val binding = FragmentFavoriteBinding.inflate(inflater)

        binding.lifecycleOwner = this

        favoriteRecycler = binding.favoriteRecycler
        favoriteSwipe = binding.favoriteSwipe

        mAdapter = FavoriteAdapter(favorites)
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
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite)
            mAdapter.notifyDataSetChanged()
        }
    }
}
