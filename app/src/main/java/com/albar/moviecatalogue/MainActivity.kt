package com.albar.moviecatalogue

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.albar.moviecatalogue.databinding.ActivityMainBinding
import com.albar.moviecatalogue.viewmodel.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.elevation = 0F
        navigationController()
    }

    private fun navigationController() {
        val navigationView = binding.bottomNav
        val navigationController = findNavController(R.id.nav_host_fragment)

        val appBarConfig = AppBarConfiguration.Builder(
            R.id.nav_movie,
            R.id.nav_fav
        ).build()

        setupActionBarWithNavController(navigationController, appBarConfig)
        navigationView.setupWithNavController(navigationController)
    }
}