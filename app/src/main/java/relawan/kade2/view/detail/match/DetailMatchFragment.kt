package relawan.kade2.view.detail.match


import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import relawan.kade2.R
import relawan.kade2.database.Favorite
import relawan.kade2.database.database
import relawan.kade2.databinding.FragmentDetailMatchBinding
import relawan.kade2.model.DetailMatch

/**
 * A simple [Fragment] subclass.
 */
class DetailMatchFragment : Fragment() {



    // TODO test isFavorite here
    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null

    private var dataDetail: DetailMatch? = null


    private lateinit var detailMatchViewModel: DetailMatchViewModel

    companion object {
        private val TAG = DetailMatchFragment::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentDetailMatchBinding.inflate(inflater)

        val application = requireNotNull(activity).application

        // lifeCycleOwner
        binding.lifecycleOwner = this

        // get argument from last/next match fragment or search fragment
        val detail = arguments?.let { DetailMatchFragmentArgs.fromBundle(it).detail }
        val search = arguments?.let { DetailMatchFragmentArgs.fromBundle(it).search }
        val viewModelFactory = DetailMatchModelFactory(detail, search, application)

        // viewModel
        detailMatchViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailMatchViewModel::class.java)

        // adapter
        val adapter = DetailMatchAdapter()
        binding.matchDetail.adapter = adapter


        // get viewModel and adapter to show list
        detailMatchViewModel.detail.observe(this, Observer {
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
                binding.progressBar.visibility = View.GONE
                adapter.data = data
            }
        })

        setHasOptionsMenu(true)
        favoriteState()
        return binding.root
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorite_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
        menuItem = menu
        setFavorite()

    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.add_favorite -> {
                if (isFavorite) deleteFavorite() else insertFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }
            else -> super.onOptionsItemSelected(item)

        }

    }




    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.setIcon(R.drawable.ic_added)
        else
            menuItem?.getItem(0)?.setIcon(R.drawable.ic_add)
    }

    // add favorite
    private fun insertFavorite() {
        try {
            context?.database?.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                    Favorite.ID_EVENT to dataDetail?.idEvent,
                    Favorite.DATE_EVENT to dataDetail?.dateEvent,
                    Favorite.TIME_EVENT to dataDetail?.strTime,
                    Favorite.HOME_TEAM to dataDetail?.strHomeTeam,
                    Favorite.AWAY_TEAM to dataDetail?.strAwayTeam,
                    Favorite.HOME_SCORE to dataDetail?.intHomeScore,
                    Favorite.AWAY_SCORE to dataDetail?.intAwayScore,
                    Favorite.HOME_GOAL_DETAILS to dataDetail?.strHomeGoalDetails,
                    Favorite.AWAY_GOAL_DETAILS to dataDetail?.strAwayGoalDetails,
                    Favorite.HOME_YELLOW_CARDS to dataDetail?.strHomeYellowCards,
                    Favorite.AWAY_YELLOW_CARDS to dataDetail?.strAwayYellowCards,
                    Favorite.HOME_RED_CARDS to dataDetail?.strHomeRedCards,
                    Favorite.AWAY_RED_CARDS to dataDetail?.strAwayRedCards
                )
            }
            Toast.makeText(context, "Add To Favorite", Toast.LENGTH_LONG).show()
        } catch (e: SQLiteConstraintException) {
            Log.e(TAG, "addFavorite = ${e.message}")
        }
    }

    // remove favorite
    private fun deleteFavorite(){
        try {
            context?.database?.use {
                delete(
                    Favorite.TABLE_FAVORITE,
                    Favorite.ID_EVENT+" ={id}",
                    "id" to dataDetail?.idEvent.toString()

                )
            }
            Toast.makeText(context, "Remove From Favorite", Toast.LENGTH_LONG).show()
        } catch (e: SQLiteConstraintException) {
            Log.e(TAG, "removeFavorite = ${e.message}")
        }
    }

    private fun favoriteState(){
        context?.database?.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs(Favorite.ID_EVENT+" ={id}",
                    "id" to dataDetail?.idEvent.toString())

            val favorite = result.parseList(classParser<Favorite>())
            if (favorite.isNotEmpty()) isFavorite = true

        }
        Log.d(TAG, isFavorite.toString())
    }


}



