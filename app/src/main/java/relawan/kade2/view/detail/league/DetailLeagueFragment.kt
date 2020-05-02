package relawan.kade2.view.detail.league


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import relawan.kade2.databinding.FragmentDetailLeagueBinding
import relawan.kade2.repository.Repository


/**
 * A simple [Fragment] subclass.
 */
class DetailLeagueFragment : Fragment() {

    private lateinit var detailLeagueViewModel: DetailLeagueViewModel

    private val repository = Repository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {


        val binding = FragmentDetailLeagueBinding.inflate(inflater)


        // lifeCycleOwner
        binding.lifecycleOwner = this

        // get arguments from HomeFragment
        val league = arguments?.let { DetailLeagueFragmentArgs.fromBundle(it).league }
        val viewModelFactory = DetailLeagueModelFactory(league, repository)

        // viewModel
        detailLeagueViewModel = ViewModelProvider(this, viewModelFactory).get(
            DetailLeagueViewModel::class.java)

        // detailLeague adapter
        val leagueAdapter = DetailLeagueAdapter()
        binding.leagueDetail.adapter = leagueAdapter

        // get viewModel and adapter to show list
        detailLeagueViewModel.detail.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.progressBarDetail.visibility = View.GONE
                binding.leagueDetail.visibility = View.VISIBLE
                binding.leagueDetail.isNestedScrollingEnabled = false
                leagueAdapter.data = it
            }
        })

        // tableLeague adapter
        val tableAdapter = TableAdapter()
        binding.leagueTable.adapter = tableAdapter

        detailLeagueViewModel.table.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.progressBarTable.visibility = View.GONE
                binding.leagueTable.visibility = View.VISIBLE
                binding.leagueTable.isNestedScrollingEnabled = false
                tableAdapter.data = it
            }
        })


        binding.lastMatch.setOnClickListener {view ->
            league?.let {league ->
                // navigate to lastMatchFragment with argument
                DetailLeagueFragmentDirections.actionDetailLeagueFragmentToLastMatchFragment(league)
            }?.let { view.findNavController().navigate(it) }

        }

        binding.nextMatch.setOnClickListener {view ->
            league?.let {league ->
                // navigate to nextMatchFragment with argument
                DetailLeagueFragmentDirections.actionDetailLeagueFragmentToNextMatchFragment(league)
            }?.let { view.findNavController().navigate(it) }

        }

        binding.leagueTeams.setOnClickListener {view ->
            league?.let {league ->
                // navigate to teamsFragment with argument
                DetailLeagueFragmentDirections.actionDetailLeagueFragmentToTeamsFragment(league)
            }?.let { view.findNavController().navigate(it) }

        }


        return binding.root
    }


}
