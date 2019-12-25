package relawan.kade2.view.detail.team


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

    private var dataTeam: DetailTeam? = null

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

        // get argument from TeamsFragment
        val teams = arguments?.let { DetailTeamFragmentArgs.fromBundle(it).teams }
        val viewModelFactory = teams?.let { DetailTeamModelFactory(context, it, repository) }

        // viewModel
        detailTeamViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailTeamViewModel::class.java)

        // adapter
        val adapter = DetailTeamAdapter()
        binding.teamDetail.adapter = adapter

        // get viewModel and adapter to show list
        detailTeamViewModel.detailTeam.observe(this, Observer {
            it?.let {data ->

                dataTeam = DetailTeam(
                    data[0].idLeague,
                    data[0].idTeam,
                    data[0].intFormedYear,
                    data[0].strCountry,
                    data[0].strDescriptionEN,
                    data[0].strStadium,
                    data[0].strTeam,
                    data[0].strTeamBadge,
                    data[0].strTeamBanner)

                // check state
                favoriteState()

                binding.progressBar.visibility = View.GONE
                adapter.data = it
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


    private fun addFavorites() {
        detailTeamViewModel.insertFavorite(dataTeam)
    }

    private fun removeFavorites() {
        detailTeamViewModel.deleteFavorite(dataTeam)
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.setIcon(R.drawable.ic_added)
        else
            menuItem?.getItem(0)?.setIcon(R.drawable.ic_add)
    }


    private fun favoriteState() {
        context?.database?.use {
            val result = select(Teams.TABLE_FAVORITE_TEAM)
                .whereArgs(Teams.ID_LEAGUE+" ={id}",
                    "id" to dataTeam?.idLeague.toString())

            val favorite = result.parseList(classParser<Teams>())
            if (favorite.isNotEmpty()) isFavorite = true

            setFavorite()
        }
        Log.d(TAG, isFavorite.toString())
    }
}
