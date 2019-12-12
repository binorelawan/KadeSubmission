package relawan.kade2.view.home


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import relawan.kade2.R
import relawan.kade2.databinding.FragmentHomeBinding
import relawan.kade2.utils.EspressoIdlingResource

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val binding = FragmentHomeBinding.inflate(inflater)

        // lifeCycleOwner
        binding.lifecycleOwner = this

        // viewModel
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)


        // adapter
        val adapter = HomeAdapter(HomeAdapter.OnClickListener {
            // navigate to detailLeagueFragment with argument
            val action = HomeFragmentDirections.actionHomeToDetailLeagueFragment(it)
            findNavController().navigate(action)
        })
        binding.leagueList.adapter = adapter

        // get viewModel and adapter to show list
        EspressoIdlingResource.increment()
        homeViewModel.leagueName.observe(this, Observer {
            it?.let {
                binding.progressBar.visibility = View.GONE
                adapter.data = it
            }
            EspressoIdlingResource.decrement()
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
        // navigate to SearchFragment
        view?.findNavController()?.navigate(R.id.action_home_to_searchFragment)
        return super.onOptionsItemSelected(item)


    }



}
