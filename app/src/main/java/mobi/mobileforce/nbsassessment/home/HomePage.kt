package mobi.mobileforce.nbsassessment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import mobi.mobileforce.nbsassessment.databinding.HomeLayoutBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.ScopeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomePage: ScopeFragment() {

    val homeViewModel by viewModel<HomeViewModel>()
    val bannerAdapter: BannerAdapter by inject()
    val popularAdapter: HomeItemAdapter by inject()
    val soonAdapter: HomeItemAdapter by inject()

    private var _binding: HomeLayoutBinding? = null
    private val binding get() = _binding!!


    companion object {
        fun newInstance(): HomePage {
            val args = Bundle()
            val fragment = HomePage()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeLayoutBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bannerSlider.adapter = bannerAdapter

        val popLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val soonLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.popularList.apply {
            layoutManager = popLayoutManager
            adapter = popularAdapter
        }
        binding.soonList.apply {
            layoutManager = soonLayoutManager
            adapter = soonAdapter
        }

        homeViewModel.getBannerUrlsData().observe(activity as AppCompatActivity, Observer {
            bannerAdapter.setItems(it)
        })

        homeViewModel.getPopUrlsData().observe(activity as AppCompatActivity, Observer {
            popularAdapter.setItems(it)
        })

        homeViewModel.getSoonUrlsData().observe(activity as AppCompatActivity, Observer {
            soonAdapter.setItems(it)
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}