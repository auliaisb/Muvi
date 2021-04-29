package mobi.mobileforce.nbsassessment.search

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import mobi.mobileforce.nbsassessment.databinding.SearchLayoutBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.ScopeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchPage: ScopeFragment() {
    val searchViewModel by viewModel<SearchViewModel>()
    val searchAdapter: SearchAdapter by inject()

    private var _binding: SearchLayoutBinding? = null
    private val binding get() = _binding!!


    companion object {
        fun newInstance(): SearchPage {
            val args = Bundle()
            val fragment = SearchPage()
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SearchLayoutBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchLayoutManager = GridLayoutManager(context, 2)
        binding.resultList.apply {
            layoutManager = searchLayoutManager
            adapter = searchAdapter
        }

        binding.searchBar.inputSearch.setOnEditorActionListener(object: TextView.OnEditorActionListener{
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                Log.d(SearchPage::class.java.canonicalName, "Editor triggered")
                if(actionId == EditorInfo.IME_ACTION_GO) {
                    searchViewModel.searchMovie(binding.searchBar.inputSearch.text.toString())
                    return true
                } else return false
            }
        })
    }
}