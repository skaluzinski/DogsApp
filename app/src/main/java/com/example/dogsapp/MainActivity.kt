package com.example.dogsapp

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.dogsapp.databinding.ActivityMainBinding
import com.example.dogsapp.quotes.ui.QuotesListFragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main), QuotesListFragment.quoteToast {

    private var notificationCount: Int = 0

    private lateinit var currentToast: Toast
    private lateinit var navController: NavController
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.dogsFragment,
                R.id.quotesFragment
            )
        )
        val toolBar = findViewById<MaterialToolbar>(R.id.topAppBar)

        val bottomNavigationView: BottomNavigationView =
            findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setupWithNavController(navController)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.dogs_page -> {
                    navController.navigate(R.id.dogsFragment)
                    true
                }
                R.id.widgets_page -> {
                    //navController.navigate()
                    true
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

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return false
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun showToast(message: String) {
        Toast.makeText(
            binding.container.context,
            message,
            Toast.LENGTH_SHORT
        ).show()

        Log.d("dupa4", "works")
//          TODO Rewrite Toast to snackbar
//        Snackbar.make(
//            binding.container.context,
//            binding.bottomNavigation,
//            "sss",
//            Snackbar.LENGTH_SHORT
//        ).setAnchorView(binding.bottomNavigation)
//            .show()
    }
}