package br.com.moviesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import br.com.moviesapp.BaseApplication
import br.com.moviesapp.R
import br.com.moviesapp.databinding.ActivityMainBinding
import br.com.moviesapp.di.main.MainComponent

class MainActivity : AppCompatActivity() {
    lateinit var mainComponent: MainComponent
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // Creates an instance of Main component by grabbing the factory from the app graph
        mainComponent = (application as BaseApplication).appComponent
            .mainComponent().create()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment? ?: return
        setupActionBarWithNavController(host.navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.main_nav_host_fragment)
        return navController.navigateUp()
    }
}