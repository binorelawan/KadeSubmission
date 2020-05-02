package relawan.kade2.view.detail.match


import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import relawan.kade2.R
import relawan.kade2.database.FavoriteMatch
import relawan.kade2.database.MyDatabase
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

    private lateinit var dataMatch: DetailMatch


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

        // database
        val application = requireNotNull(this.activity).application
        val dataSource = MyDatabase.getInstance(application).favoriteMatchDao

        // get argument from last/next match fragment or search fragment
        val detail = arguments?.let { DetailMatchFragmentArgs.fromBundle(it).detail }
        val search = arguments?.let { DetailMatchFragmentArgs.fromBundle(it).search }
        val favorite = arguments?.let { DetailMatchFragmentArgs.fromBundle(it).favorite }
        val viewModelFactory = DetailMatchModelFactory(dataSource, detail, search, favorite, repository)

        // viewModel
        detailMatchViewModel = ViewModelProvider(this, viewModelFactory).get(DetailMatchViewModel::class.java)

        // adapter
        val adapter = DetailMatchAdapter()
        binding.matchDetail.adapter = adapter




        // get viewModel and adapter to show list
        detailMatchViewModel.detailMatch.observe(viewLifecycleOwner, Observer {
            it?.let {database ->

                dataMatch = DetailMatch(
                    database[0].idEvent,
                    database[0].dateEvent,
                    database[0].strTime,
                    database[0].idAwayTeam,
                    database[0].idHomeTeam,
                    database[0].strAwayTeam,
                    database[0].strHomeTeam,
                    database[0].intAwayScore,
                    database[0].intHomeScore,
                    database[0].strAwayGoalDetails,
                    database[0].strHomeGoalDetails,
                    database[0].strAwayRedCards,
                    database[0].strHomeRedCards,
                    database[0].strAwayYellowCards,
                    database[0].strHomeYellowCards
                )

                // check state
                checkFavorite()

                binding.progressBar.visibility = View.GONE
                adapter.data = database
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun checkFavorite() {
        detailMatchViewModel.isCheck.observe(viewLifecycleOwner, Observer {
            isFavorite = it
            setFavorite()
        })
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


    private fun addFavorites() {
        detailMatchViewModel.insert(
            FavoriteMatch(
                idEvent = dataMatch.idEvent!!,
                dateEvent = dataMatch.dateEvent,
                strTime = dataMatch.strTime,
                idAwayTeam = dataMatch.idAwayTeam,
                idHomeTeam = dataMatch.idHomeTeam,
                strAwayTeam = dataMatch.strAwayTeam,
                strHomeTeam = dataMatch.strHomeTeam,
                intAwayScore = dataMatch.intAwayScore,
                intHomeScore = dataMatch.intHomeScore,
                strAwayGoalDetails = dataMatch.strAwayGoalDetails,
                strHomeGoalDetails = dataMatch.strHomeGoalDetails,
                strAwayRedCards = dataMatch.strAwayRedCards,
                strHomeRedCards = dataMatch.strHomeRedCards,
                strAwayYellowCards = dataMatch.strAwayYellowCards,
                strHomeYellowCards = dataMatch.strHomeYellowCards
            )
        )
        Log.d(TAG, "fun add $isFavorite")
        Toast.makeText(context, "favorite = ${dataMatch.idEvent}", Toast.LENGTH_LONG).show()
    }


    private fun removeFavorites() {
        detailMatchViewModel.delete(
            FavoriteMatch(
                idEvent = dataMatch.idEvent!!,
                dateEvent = dataMatch.dateEvent,
                strTime = dataMatch.strTime,
                idAwayTeam = dataMatch.idAwayTeam,
                idHomeTeam = dataMatch.idHomeTeam,
                strAwayTeam = dataMatch.strAwayTeam,
                strHomeTeam = dataMatch.strHomeTeam,
                intAwayScore = dataMatch.intAwayScore,
                intHomeScore = dataMatch.intHomeScore,
                strAwayGoalDetails = dataMatch.strAwayGoalDetails,
                strHomeGoalDetails = dataMatch.strHomeGoalDetails,
                strAwayRedCards = dataMatch.strAwayRedCards,
                strHomeRedCards = dataMatch.strHomeRedCards,
                strAwayYellowCards = dataMatch.strAwayYellowCards,
                strHomeYellowCards = dataMatch.strHomeYellowCards
            )
        )
        Log.d(TAG, "fun delete $isFavorite")
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.setIcon(R.drawable.ic_added)
        else
            menuItem?.getItem(0)?.setIcon(R.drawable.ic_add)
    }



}



