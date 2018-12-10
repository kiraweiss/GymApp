package com.example.roxanahayne.gymapp

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.app_bar_search.*

class SearchActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {



    companion object{
        const val KEY_DATA = "KEY_DATA"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setSupportActionBar(toolbarSearch)

        fabSearch.setOnClickListener {

//
//            AppDatabase.getInstance(
//                this@SearchActivity
//            ).gymDao().insertGym(Gym(null, "cats"))
//
//            AppDatabase.getInstance(
//                this@SearchActivity
//            ).gymDao().insertGym(Gym(null, "dogs"))
//
//            AppDatabase.getInstance(
//                this@SearchActivity
//            ).gymDao().insertGym(Gym(null, "none"))




        }

        btnSubmit.setOnClickListener{

//            var intent = Intent(this@SearchActivity, ResultsActivity::class.java)
//            intent.putExtra(KEY_DATA, etSearch.text)
//            startActivity(intent)

            startActivity(Intent(this@SearchActivity, ResultsActivity::class.java))
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbarSearch, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)


    }




    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.search, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {

                startActivity(Intent(this@SearchActivity, HomeActivity::class.java))

            }
            R.id.nav_about -> {
                Toast.makeText(applicationContext, "about", Toast.LENGTH_LONG).show()

            }
            R.id.nav_other -> {
                Toast.makeText(applicationContext, "other", Toast.LENGTH_LONG).show()

            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
