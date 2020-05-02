package relawan.kade2.view.detail.team


import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import relawan.kade2.R
import relawan.kade2.database.FavoriteTeam
import relawan.kade2.database.MyDatabase
import relawan.kade2.database.database
import relawan.kade2.databinding.FragmentDetailTeamBinding
import relawan.kade2.model.DetailTeam
import relawan.kade2.model.Teams
import relawan.kade2.repository.Repository

/**
 * A simple [Fragment] subclass.
 */
class DetailTeamFragment : Fragment() {

    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null

    private lateinit var dataTeam: DetailTeam

    private lateinit var detailTeamViewModel: DetailTeamViewModel

    private val repository = Repository()

    companion object {
        private val TAG = DetailTeamFragment::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentDetailTeamBinding.inflate(inflater)

        // lifeCycleOwner
        binding.lifecycleOwner = this

        // database
        val application = requireNotNull(this.activity).application
        val dataSource = MyDatabase.getInstance(application).favoriteTeamDao

        // get argument from TeamsFragment
        val teams = arguments?.let { DetailTeamFragmentArgs.fromBundle(it).teams }
        val search = arguments?.let { DetailTeamFragmentArgs.fromBundle(it).search }
        val favorite = arguments?.let { DetailTeamFragmentArgs.fromBundle(it).favorite }
        val viewModelFactory = DetailTeamModelFactory(dataSource, teams, search, favorite, repository)

        // viewModel
        detailTeamViewModel = ViewModelProvider(this, viewModelFactory).get(DetailTeamViewModel::class.java)

        // adapter
        val adapter = DetailTeamAdapter()
        binding.teamDetail.adapter = adapter

        // get viewModel and adapter to show list
        detailTeamViewModel.detailTeam.observe(viewLifecycleOwner, Observer {
            it?.let {database ->

                dataTeam = DetailTeam(
                    database[0].idTeam,
                    database[0].idLeague,
                    database[0].intFormedYear,
                    database[0].strCountry,
                    database[0].strDescriptionEN,
                    database[0].strStadium,
                    database[0].strTeam,
                    database[0].strTeamBadge,
                    database[0].strTeamBanner
                )

                // check state
                checkFavorite()

                binding.progressBar.visibility = View.GONE
                adapter.data = it
            }

        })

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun checkFavorite() {
        detailTeamViewModel.isCheck.observe(viewLifecycleOwner, Observer {
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
        detailTeamViewModel.insert(
            FavoriteTeam(
                idTeam = dataTeam.idTeam!!,
                idLeague = dataTeam.idLeague,
                intFormedYear = dataTeam.intFormedYear,
                strCountry = dataTeam.strCountry,
                strDescriptionEN = dataTeam.strDescriptionEN,
                strStadium = dataTeam.strStadium,
                strTeam = dataTeam.strTeam,
                strTeamBadge = dataTeam.strTeamBadge,
                strTeamBanner = dataTeam.strTeamBanner
            )
        )
        Toast.makeText(context, "Add Favorites", Toast.LENGTH_LONG).show()
    }

    private fun removeFavorites() {
        detailTeamViewModel.delete(
            FavoriteTeam(
                idTeam = dataTeam.idTeam!!,
                idLeague = dataTeam.idLeague,
                intFormedYear = dataTeam.intFormedYear,
                strCountry = dataTeam.strCountry,
                strDescriptionEN = dataTeam.strDescriptionEN,
                strStadium = dataTeam.strStadium,
                strTeam = dataTeam.strTeam,
                strTeamBadge = dataTeam.strTeamBadge,
                strTeamBanner = dataTeam.strTeamBanner
            )
        )
        Toast.makeText(context, "Remove Favorites", Toast.LENGTH_LONG).show()

    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.setIcon(R.drawable.ic_added)
        else
            menuItem?.getItem(0)?.setIcon(R.drawable.ic_add)
    }


}
