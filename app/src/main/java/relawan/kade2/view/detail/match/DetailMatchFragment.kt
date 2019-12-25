package relawan.kade2.view.detail.match


import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import relawan.kade2.R
import relawan.kade2.database.database
import relawan.kade2.databinding.FragmentDetailMatchBinding
import relawan.kade2.model.DetailMatch
import relawan.kade2.model.Match
import relawan.kade2.repository.Repository

/**
 * A simple [Fragment] subclass.
 */
class DetailMatchFragment : Fragment() {



    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null

    private var dataDetail: DetailMatch? = null


    private lateinit var detailMatchViewModel: DetailMatchViewModel

    private val repository = Repository()

    companion object {
        private val TAG = DetailMatchFragment::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentDetailMatchBinding.inflate(inflater)


        // lifeCycleOwner
        binding.lifecycleOwner = this

        // get argument from last/next match fragment or search fragment
        val detail = arguments?.let { DetailMatchFragmentArgs.fromBundle(it).detail }
        val search = arguments?.let { DetailMatchFragmentArgs.fromBundle(it).search }
        val viewModelFactory = DetailMatchModelFactory(context, detail, search, repository)

        // viewModel
        detailMatchViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailMatchViewModel::class.java)

        // adapter
        val adapter = DetailMatchAdapter()
        binding.matchDetail.adapter = adapter




        // get viewModel and adapter to show list
        detailMatchViewModel.detailMatch.observe(this, Observer {
            it?.let {data ->

                dataDetail = DetailMatch(
                    data[0].dateEvent,
                    data[0].idAwayTeam,
                    data[0].idEvent,
                    data[0].idHomeTeam,
                    data[0].intAwayScore,
                    data[0].intHomeScore,
                    data[0].strAwayGoalDetails,
                    data[0].strAwayRedCards,
                    data[0].strAwayTeam,
                    data[0].strAwayYellowCards,
                    data[0].strHomeGoalDetails,
                    data[0].strHomeRedCards,
                    data[0].strHomeTeam,
                    data[0].strHomeYellowCards,
                    data[0].strTime)

                // check state
                favoriteState()

                binding.progressBar.visibility = View.GONE
                adapter.data = data
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorite_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
        menuItem = menu

    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.add_favorite -> {
                if (isFavorite) removeFavorites() else addFavorites()

                isFavorite = !isFavorite
                setFavorite()

                true
            }
            else -> super.onOptionsItemSelected(item)

        }

    }


    private fun addFavorites(){
        detailMatchViewModel.insertFavorite(dataDetail)
    }

    private fun removeFavorites(){
        detailMatchViewModel.deleteFavorite(dataDetail)
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.setIcon(R.drawable.ic_added)
        else
            menuItem?.getItem(0)?.setIcon(R.drawable.ic_add)
    }


    private fun favoriteState(){
        context?.database?.use {
            val result = select(Match.TABLE_FAVORITE_MATCH)
                .whereArgs(Match.ID_EVENT+" ={id}",
                    "id" to dataDetail?.idEvent.toString())

            val favorite = result.parseList(classParser<Match>())
            if (favorite.isNotEmpty()) isFavorite = true

            setFavorite()

        }
        Log.d(TAG, isFavorite.toString())
    }


}



