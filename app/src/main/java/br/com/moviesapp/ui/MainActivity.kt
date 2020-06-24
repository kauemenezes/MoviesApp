package br.com.moviesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import br.com.moviesapp.BaseApplication
import br.com.moviesapp.R
import br.com.moviesapp.di.main.MainComponent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var mainComponent: MainComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        // Creates an instance of Main component by grabbing the factory from the app graph
        mainComponent = (application as BaseApplication).appComponent
            .mainComponent().create()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        setSupportActionBar(toolbar)
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment? ?: return
        setupActionBarWithNavController(host.navController)
    }
}