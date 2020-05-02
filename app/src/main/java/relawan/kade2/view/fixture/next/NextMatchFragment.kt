package relawan.kade2.view.fixture.next


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import relawan.kade2.databinding.FragmentNextMatchBinding
import relawan.kade2.repository.Repository

/**
 * A simple [Fragment] subclass.
 */
class NextMatchFragment : Fragment() {

    private lateinit var nextMatchViewModel: NextMatchViewModel

    private val repository = Repository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val binding = FragmentNextMatchBinding.inflate(inflater)


        // lifeCycleOwner
        binding.lifecycleOwner = this

        // get arguments
        val league = arguments?.let { NextMatchFragmentArgs.fromBundle(it).league }
        val viewModelFactory = NextMatchModelFactory(league, repository)

        // viewModel
        nextMatchViewModel = ViewModelProvider(this, viewModelFactory).get(NextMatchViewModel::class.java)

        // adapter
        val adapter = NextMatchAdapter(NextMatchAdapter.OnClickListener{
            // navigate to detailMatchFragment with argument
            val action = NextMatchFragmentDirections.actionNextMatchFragmentToDetailMatchFragment(it, null, null)
            findNavController().navigate(action)
        })
        binding.leagueNextMatch.adapter = adapter

        // get viewModel and adapter to show list
        nextMatchViewModel.nextMatch.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.progressBar.visibility = View.GONE
                adapter.data = it
            }
        })


        return binding.root
    }


}
