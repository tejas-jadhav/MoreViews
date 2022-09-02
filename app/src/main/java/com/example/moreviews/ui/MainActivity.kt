package com.example.moreviews.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.moreviews.R
import com.example.moreviews.databinding.ActivityMainBinding
import com.example.moreviews.ui.favorites.FavoritesActivity
import com.example.moreviews.ui.main.ConvertFragment
import com.example.moreviews.ui.main.FormFragment
import com.example.moreviews.ui.main.RandomFragment
import com.example.moreviews.ui.settings.SettingsActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val convertFragment = ConvertFragment()
        val formFragment = FormFragment()
        val randomFragment = RandomFragment()

        setCurrentFragment(convertFragment)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.miForm -> setCurrentFragment(formFragment)
                R.id.miConvert -> setCurrentFragment(convertFragment)
                R.id.miRandom -> setCurrentFragment(randomFragment)
            }
            true
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.miFavorites -> launchFavorites()
            R.id.miSettings -> launchSettings()
            R.id.miExit -> finish()
        }
        return true
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flMain, fragment)
            commit()
        }
    }

    private fun launchFavorites() {
        Intent(this, FavoritesActivity::class.java).apply {
            startActivity(this)
        }
    }

    private fun launchSettings() {
        Intent(this, SettingsActivity::class.java).apply {
            startActivity(this)
        }
    }
}