package relawan.kade2.view.fixture.search


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
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
import relawan.kade2.databinding.FragmentSearchBinding
import relawan.kade2.model.Search

/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment() {

    lateinit var searchView: SearchView
    lateinit var progressBar: ProgressBar
    lateinit var matchList: RecyclerView
    lateinit var errorText: TextView
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var searchAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_search, container, false)

        val binding = FragmentSearchBinding.inflate(inflater)

        // lifeCycleOwner
        binding.lifecycleOwner = this

        // init searchView, progressBar, recyclerView, textView
        searchView = binding.searchMatch
        progressBar = binding.progressBar
        matchList = binding.matchList
        errorText = binding.errorText

        // viewModel
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        // adapter
        searchAdapter = SearchAdapter(SearchAdapter.OnClickListener{
            // navigate to detailMatchFragment with argument
            val action = SearchFragmentDirections.actionSearchFragmentToDetailMatchFragment(null, it)
            findNavController().navigate(action)
        })
        matchList.adapter = searchAdapter


        initSearch()

        return binding.root
    }

    // get viewModel and adapter to show list
    private fun searchMatch(query: String) {


        searchViewModel.getSearchMatch(query).observe(this, Observer<List<Search>?> {list ->
            // filter strSport == "Soccer"
            val filter = list?.filter {search ->
                search.strSport == "Soccer"
            }


            if (list == filter && list != null) {
                progressBar.visibility = View.GONE
                matchList.visibility = View.VISIBLE
                searchAdapter.data = list
            } else {
                // when input failed
                progressBar.visibility = View.GONE
                errorText.visibility = View.VISIBLE
            }


        })

    }

    // searchView
    private fun initSearch() {

        val searchManager = this.context?.getSystemService(Context.SEARCH_SERVICE) as SearchManager?
        searchView.setSearchableInfo(searchManager!!.getSearchableInfo(activity?.componentName))

        // activate keyboard
        searchView.onActionViewExpanded()

        val imm = this.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm!!.showSoftInput(searchView, InputMethodManager.SHOW_IMPLICIT)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                if (query.isNotEmpty()) {

                    searchMatch(query)
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


}
