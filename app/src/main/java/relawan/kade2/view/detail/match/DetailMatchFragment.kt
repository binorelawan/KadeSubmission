package relawan.kade2.view.detail.match


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import relawan.kade2.databinding.FragmentDetailMatchBinding

/**
 * A simple [Fragment] subclass.
 */
class DetailMatchFragment : Fragment() {

    private lateinit var detailMatchViewModel: DetailMatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentDetailMatchBinding.inflate(inflater)

        val application = requireNotNull(activity).application

        // // lifeCycleOwner
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
            it?.let {
                binding.progressBar.visibility = View.GONE
                adapter.data = it
            }
        })


        return binding.root
    }
}



