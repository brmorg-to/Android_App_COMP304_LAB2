package com.hfad.brunomorgado_comp304sec002_lab02_exercise1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.hfad.brunomorgado_comp304sec002_lab02_exercise1.databinding.ActivityHomeTypesBinding

class HomeTypes : AppCompatActivity() {

    private lateinit var binding: ActivityHomeTypesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeTypesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        displayFragment(Select())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.types_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.apartment -> {
                displayFragment(Apartment())
                return true
            }
            R.id.detached -> {
                displayFragment(DetachedHome())
                return true
            }
            R.id.semiDetached -> {
                displayFragment(SemiDetachedHome())
                return true
            }
            R.id.condominium -> {
                displayFragment(Condominium())
                return true
            }
            R.id.townHouse -> {
                displayFragment(TownHouse())
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun displayFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}