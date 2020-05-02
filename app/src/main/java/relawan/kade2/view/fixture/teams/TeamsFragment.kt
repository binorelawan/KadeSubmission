package relawan.kade2.view.fixture.teams


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import relawan.kade2.R
import relawan.kade2.databinding.FragmentTeamsBinding
import relawan.kade2.repository.Repository

/**
 * A simple [Fragment] subclass.
 */
class TeamsFragment : Fragment() {

    private lateinit var teamsViewModel: TeamsViewModel

    private val repository = Repository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val binding = FragmentTeamsBinding.inflate(inflater)

        // lifeCycleOwner
        binding.lifecycleOwner = this

        // get arguments
        val league = arguments?.let { TeamsFragmentArgs.fromBundle(it).league }
        val viewModelFactory = TeamsModelFactory(league, repository)

        // viewModel
        teamsViewModel = ViewModelProvider(this, viewModelFactory).get(TeamsViewModel::class.java)

        // adapter
        val adapter = TeamsAdapter(TeamsAdapter.OnClickListener {
            // navigate to detailTeamFragment with argument
            val action = TeamsFragmentDirections.actionTeamsFragmentToDetailTeamFragment(it, null, null)
            findNavController().navigate(action)
        })
        binding.teamsList.adapter = adapter

        // get viewModel and adapter to show list
        teamsViewModel.teams.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.progressBar.visibility = View.GONE
                adapter.data = it
            }
        })

        // enable menu
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return  when(item.itemId) {
            R.id.search_menu -> {
                // navigate to SearchTeamFragment
                view?.findNavController()?.navigate(R.id.action_teamsFragment_to_searchTeamFragment)

                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }
}
