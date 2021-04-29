package mobi.mobileforce.nbsassessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import mobi.mobileforce.nbsassessment.databinding.ActivityMainBinding
import mobi.mobileforce.nbsassessment.home.HomePage
import mobi.mobileforce.nbsassessment.search.SearchPage
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding : ActivityMainBinding
    private val homePage = HomePage.newInstance()
    private val searchPage = SearchPage.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.navigation.setOnNavigationItemSelectedListener(this)

        setContentView(binding.root)

        loadFragment(homePage)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.home -> loadFragment(homePage)
            R.id.popular -> loadFragment(searchPage)
            R.id.favorite -> true
            else -> false
        }
        return false
    }

    fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, fragment)
            .commit()
    }
}