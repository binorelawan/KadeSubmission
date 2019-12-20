package relawan.kade2.view.fixture.last


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import relawan.kade2.databinding.FragmentLastMatchBinding
import relawan.kade2.repository.Repository

/**
 * A simple [Fragment] subclass.
 */
class LastMatchFragment : Fragment() {

    private lateinit var lastMatchViewModel: LastMatchViewModel

    private val repository = Repository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {


        val binding = FragmentLastMatchBinding.inflate(inflater)


        // lifeCycleOwner
        binding.lifecycleOwner = this

        // get arguments
        val league = arguments?.let { LastMatchFragmentArgs.fromBundle(it).league }
        val viewModelFactory = league?.let { LastMatchModelFactory(it, repository) }

        // viewModel
        lastMatchViewModel = ViewModelProviders.of(this, viewModelFactory).get(LastMatchViewModel::class.java)

        // adapter
        val adapter = LastMatchAdapter(LastMatchAdapter.OnClickListener{
            // navigate to detailMatchFragment with argument
            val action = LastMatchFragmentDirections.actionLastMatchFragmentToDetailMatchFragment(it, null)
            findNavController().navigate(action)
        })
        binding.leagueLastMatch.adapter = adapter

        // get viewModel and adapter to show list
        lastMatchViewModel.lastMatch.observe(this, Observer {
            it?.let {
                binding.progressBar.visibility = View.GONE
                adapter.data = it
            }
        })


        return binding.root
    }


}
