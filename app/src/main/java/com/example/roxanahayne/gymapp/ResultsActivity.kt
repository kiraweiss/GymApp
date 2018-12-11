package com.example.roxanahayne.gymapp

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.roxanahayne.gymapp.adapter.GymAdapter
import com.example.roxanahayne.gymapp.data.AppDatabase
import com.example.roxanahayne.gymapp.data.Gym
import com.example.roxanahayne.gymapp.touch.GymTouchHelperCallback
import kotlinx.android.synthetic.main.activity_results.*
import kotlinx.android.synthetic.main.app_bar_results.*

class ResultsActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var gymAdapter: GymAdapter
    private lateinit var searchName: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        setSupportActionBar(toolbarResults)

        fabResults.setOnClickListener { view ->

            startActivity(Intent(this@ResultsActivity, DetailsActivity::class.java))
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbarResults, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        searchName = "None"

        if (intent.hasExtra(SearchActivity.KEY_DATA)) {
            searchName = intent.getStringExtra(SearchActivity.KEY_DATA)

        }

        initRecyclerView()

    }


    private fun initRecyclerView() {
        Thread {
//            val gymList = AppDatabase.getInstance(
//                this@ResultsActivity
//            ).gymDao().findAllGyms()

//                //findByName(searchName)

            val gymList : List<Gym> = listOf(Gym(null, "click on this to open map page"),
                Gym(null, "or this one"), Gym(null, "or this one"))

            gymAdapter = GymAdapter(
                this@ResultsActivity,
                gymList
            )

            runThreadOp()

        }.start()
    }

    private fun runThreadOp() {

        runOnUiThread {

            this.recyclerGym.adapter = gymAdapter
            val callback = GymTouchHelperCallback(gymAdapter)
            val touchHelper = ItemTouchHelper(callback)
            touchHelper.attachToRecyclerView(recyclerGym)

        }
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
        menuInflater.inflate(R.menu.results, menu)
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
            R.id.nav_search -> {

                startActivity(Intent(this@ResultsActivity, SearchActivity::class.java))
            }
            R.id.nav_home -> {

                startActivity(Intent(this@ResultsActivity, HomeActivity::class.java))

            }
            R.id.nav_about -> {
                Toast.makeText(applicationContext, "about", Toast.LENGTH_LONG).show()
            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
