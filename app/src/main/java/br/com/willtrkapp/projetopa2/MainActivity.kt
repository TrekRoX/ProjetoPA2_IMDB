package br.com.willtrkapp.projetopa2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.app_name)


        val abreFechaToogle: ActionBarDrawerToggle =
            ActionBarDrawerToggle(
                this,
                drawer_layout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            )
        drawer_layout.addDrawerListener(abreFechaToogle)
        abreFechaToogle.syncState()
    }

    override fun onBackPressed() {
        if(drawer_layout.isDrawerOpen(GravityCompat.START))  {
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        else  {
            super.onBackPressed()
        }
    }
}
