package relawan.kade2

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import relawan.kade2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        bottomNav = binding.bottomNavView

        val navController = this.findNavController(R.id.nav_host_fragment)

        // This code for remove back button from action bar in fragment
        val appBarConfiguration = AppBarConfiguration
            .Builder(
                R.id.home,
                R.id.favoriteMatchFragment,
                R.id.favoriteTeamFragment)
            .build()


        bottomNavView(navController)

        bottomNav.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
    }

    // show back up button
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return navController.navigateUp()
    }

    // show bottom navigation view in specified fragment
    private fun bottomNavView(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.home -> showBottomNav()
                R.id.favoriteMatchFragment -> showBottomNav()
                R.id.favoriteTeamFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }



    /*
    show/hide bottom navigation
     */
    private fun showBottomNav() {
        bottomNav.visibility = View.VISIBLE
    }
    private fun hideBottomNav() {
        bottomNav.visibility = View.GONE
    }
}
