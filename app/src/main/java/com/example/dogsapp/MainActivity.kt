package com.example.dogsapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.dogsapp.databinding.ActivityMainBinding
import com.example.dogsapp.dogs.ui.SingleBreedPhotos
import com.example.dogsapp.quotes.ui.QuotesListFragment
import com.example.dogsapp.widgets.NewAppWidgetConfigureActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), QuotesListFragment.quoteSnackbar,
    SingleBreedPhotos.showDogSnackbar {

    private lateinit var navController: NavController
    private var currentSnackbar: Snackbar? = null
    private var currentSnackbarCount = 1
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.dogsFragment,
                R.id.quotesFragment
            )
        )
        val toolBar = binding.topAppBar
        toolBar.setupWithNavController(navController, appBarConfiguration)
        val bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.setupWithNavController(navController)

        toolBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_profile -> {
                    navController.navigate(R.id.settingsFragment)
                    true
                }
                else -> false
            }

        }
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.dogs_page -> {
                    navController.navigate(R.id.dogsFragment)
                    true
                }
                R.id.widgets_page -> {
                    false
                }
                R.id.quotes_page -> {
                    navController.navigate(R.id.quotesFragment)
                    true
                }
                else -> {
                    false
                }
            }
        }

        setContentView(binding.root)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return false
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


    override fun quoteSnackbar(message: String) {
        Snackbar.make(
            binding.container,
            message,
            Snackbar.LENGTH_SHORT
        ).setAnchorView(binding.bottomNavigation)
            .show()
    }

    override fun showDogSnackbar(breedName: String) {
        if (currentSnackbar?.isShown == true) {
            currentSnackbarCount++
            currentSnackbar = Snackbar.make(
                binding.container,
                "You downloaded $currentSnackbarCount images.",
                Snackbar.LENGTH_SHORT
            ).setAnchorView(binding.bottomNavigation)
            currentSnackbar!!.show()
        } else {
            currentSnackbarCount = 1
            currentSnackbar = Snackbar.make(
                binding.container,
                "You downloaded image of $breedName.",
                Snackbar.LENGTH_SHORT
            ).setAnchorView(binding.bottomNavigation)
            currentSnackbar!!.show()
        }
    }


}