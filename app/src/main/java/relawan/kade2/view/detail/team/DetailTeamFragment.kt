package relawan.kade2.view.detail.team


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import relawan.kade2.databinding.FragmentDetailTeamBinding
import relawan.kade2.repository.Repository

/**
 * A simple [Fragment] subclass.
 */
class DetailTeamFragment : Fragment() {

    private lateinit var detailTeamViewModel: DetailTeamViewModel

    private val repository = Repository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentDetailTeamBinding.inflate(inflater)

        // lifeCycleOwner
        binding.lifecycleOwner = this

        // get argument from TeamsFragment
        val teams = arguments?.let { DetailTeamFragmentArgs.fromBundle(it).teams }
        val viewModelFactory = teams?.let { DetailTeamModelFactory(it, repository) }

        // viewModel
        detailTeamViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailTeamViewModel::class.java)

        // adapter
        val adapter = DetailTeamAdapter()
        binding.teamDetail.adapter = adapter

        // get viewModel and adapter to show list
        detailTeamViewModel.detailTeam.observe(this, Observer {
            it?.let {
                binding.progressBar.visibility = View.GONE
                binding.teamDetail.visibility = View.VISIBLE
                adapter.data = it
            }

        })
        return binding.root
    }


}
