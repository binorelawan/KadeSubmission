package relawan.kade2.view.fixture.search.match


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import relawan.kade2.databinding.FragmentSearchMatchBinding
import relawan.kade2.repository.Repository

/**
 * A simple [Fragment] subclass.
 */
class SearchMatchFragment : Fragment() {

    private lateinit var searchView: SearchView
    private lateinit var progressBar: ProgressBar
    private lateinit var matchList: RecyclerView
    private lateinit var errorText: TextView
    private lateinit var searchMatchViewModel: SearchMatchViewModel
    private lateinit var searchMatchAdapter: SearchMatchAdapter

    private val repository = Repository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val binding = FragmentSearchMatchBinding.inflate(inflater)

        // lifeCycleOwner
        binding.lifecycleOwner = this

        // init searchView, progressBar, recyclerView, textView
        searchView = binding.searchMatch
        progressBar = binding.progressBar
        matchList = binding.matchList
        errorText = binding.errorText

        val viewModelFactory =
            SearchMatchModelFactory(repository)

        // viewModel
        searchMatchViewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchMatchViewModel::class.java)

        // adapter
        searchMatchAdapter =
            SearchMatchAdapter(SearchMatchAdapter.OnClickListener {
                // navigate to detailMatchFragment with argument
                val action =
                    SearchMatchFragmentDirections.actionSearchMatchFragmentToDetailMatchFragment(
                        null,
                        it
                    )
                findNavController().navigate(action)
                // close keyboard after click enter
                searchView.clearFocus()
            })
        matchList.adapter = searchMatchAdapter


        initSearch()
        searchMatch()

        setHasOptionsMenu(true)
        return binding.root
    }

    // get viewModel and adapter to show list
    private fun searchMatch() {

        searchMatchViewModel.search.observe(this, Observer { list ->
            // filter strSport == "Soccer"
            val filter = list?.filter {search ->
                search.strSport == "Soccer"

            }

            if (list != null) {
                Log.d(TAG, "list not null: ${list.size}")

                if (filter != null) {
                    if (filter.isEmpty()) {
                        Log.d(TAG, "filter empty: ${filter.size}")
                        progressBar.visibility = View.GONE
                        errorText.visibility = View.VISIBLE

                    } else {
                        Log.d(TAG, "filter not empty: ${filter.size}")
                        progressBar.visibility = View.GONE
                        matchList.visibility = View.VISIBLE
                        searchMatchAdapter.data = filter

                    }
                }

            } else {
                Log.d(TAG, "list null: ${list?.size}")
                // when input failed
                progressBar.visibility = View.GONE
                errorText.visibility = View.VISIBLE

            }

        })
    }

    // searchView
    private fun initSearch() {

        val searchManager = this.context?.getSystemService(Context.SEARCH_SERVICE) as SearchManager?
        searchView.setSearchableInfo(searchManager?.getSearchableInfo(activity?.componentName))

        // activate keyboard
        searchView.onActionViewExpanded()

        val imm = this.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm!!.showSoftInput(searchView, InputMethodManager.SHOW_IMPLICIT)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                if (query.isNotEmpty()) {

                    searchMatchViewModel.getSearchMatch(query)
                    progressBar.visibility = View.VISIBLE
                    matchList.visibility = View.GONE
                    errorText.visibility = View.GONE
                    // close keyboard after click enter
                    searchView.clearFocus()

                }

                return false
            }

            override fun onQueryTextChange(queryText: String): Boolean {


                return false
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // close keyboard after click back up button
        searchView.clearFocus()
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private val TAG = SearchMatchFragment::class.java.simpleName
    }
}
