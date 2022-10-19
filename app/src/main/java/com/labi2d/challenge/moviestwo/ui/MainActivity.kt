package com.labi2d.challenge.moviestwo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.labi2d.challenge.moviestwo.R
import com.labi2d.challenge.moviestwo.databinding.NavigationActivityBinding

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(NavigationActivityBinding.inflate(layoutInflater)) {
            setContentView(root)
            setSupportActionBar(toolbar)

            val navHostFragment: NavHostFragment = myNavHostFragment.getFragment()
            val navController = navHostFragment.navController

            val topLevelDestinations = setOf(R.id.home_dest, R.id.movies_dest, R.id.series_dest)
            appBarConfiguration = AppBarConfiguration(topLevelDestinations)

            setupActionBar(navController, appBarConfiguration)
            setupBottomNavMenu(bottomNavView, navController)
        }

    }

    private fun setupActionBar(navController: NavController, appBarConfig: AppBarConfiguration) {
        setupActionBarWithNavController(navController, appBarConfig)
    }

    private fun setupBottomNavMenu(bottomNav: BottomNavigationView, navController: NavController) {
        bottomNav.setupWithNavController(navController)
    }
}