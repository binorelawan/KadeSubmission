package relawan.kade2.view.favorite.match


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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

    private lateinit var favoriteMatchViewModel: FavoriteMatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {


        val binding = FragmentFavoriteMatchBinding.inflate(inflater)

        binding.lifecycleOwner = this

        favoriteMatchViewModel = ViewModelProvider(this).get(FavoriteMatchViewModel::class.java)

        val adapter = FavoriteMatchAdapter(FavoriteMatchAdapter.OnClickListener {
            // navigate to detailMatchFragment with argument
            val action = FavoriteMatchFragmentDirections.actionFavoriteMatchFragmentToDetailMatchFragment(null, null, it.idEvent)
            findNavController().navigate(action)
            Toast.makeText(context, it.idEvent, Toast.LENGTH_LONG).show()
        })

        binding.favoriteRecycler.adapter = adapter

        favoriteMatchViewModel.favoriteMatch.observe(viewLifecycleOwner, Observer {
            it.let {
                adapter.data = it
            }
        })

        return binding.root

    }


}
