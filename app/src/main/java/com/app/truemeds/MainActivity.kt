package com.app.truemeds

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mNavHostFragment: NavHostFragment
    private lateinit var mGraph: NavGraph
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mNavHostFragment =
            (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment)
        mGraph = mNavHostFragment.navController.navInflater.inflate(R.navigation.nav_main)
    }
}