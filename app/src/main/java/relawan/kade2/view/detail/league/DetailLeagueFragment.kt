package relawan.kade2.view.detail.league


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import relawan.kade2.databinding.FragmentDetailLeagueBinding



/**
 * A simple [Fragment] subclass.
 */
class DetailLeagueFragment : Fragment() {

    private lateinit var detailLeagueViewModel: DetailLeagueViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {


        val binding = FragmentDetailLeagueBinding.inflate(inflater)

        val application = requireNotNull(activity).application

        // lifeCycleOwner
        binding.lifecycleOwner = this

        // get arguments from HomeFragment
        val league = arguments?.let { DetailLeagueFragmentArgs.fromBundle(it).league }
        val viewModelFactory = league?.let { DetailLeagueModelFactory(it, application) }

        // viewModel
        detailLeagueViewModel = ViewModelProviders.of(this, viewModelFactory).get(
            DetailLeagueViewModel::class.java)

        // adapter
        val adapter = DetailLeagueAdapter()
        binding.leagueDetail.adapter = adapter

        // get viewModel and adapter to show list
        detailLeagueViewModel.detail.observe(this, Observer {
            it?.let {
                binding.progressBar.visibility = View.GONE
                binding.leagueDetail.visibility = View.VISIBLE
                adapter.data = it
            }
        })


        binding.lastMatch.setOnClickListener {view ->
            league?.let {
                // navigate to lastMatchFragment with argument
                DetailLeagueFragmentDirections.actionDetailLeagueFragmentToLastMatchFragment(
                    it
                )
            }?.let { view.findNavController().navigate(it) }

        }

        binding.nextMatch.setOnClickListener {view ->
            league?.let {
                // navigate to nextMatchFragment with argument
                DetailLeagueFragmentDirections.actionDetailLeagueFragmentToNextMatchFragment(
                    it
                )
            }?.let { view.findNavController().navigate(it) }

        }


        return binding.root
    }


}
