package br.com.willtrkapp.projetopa2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

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

        //Listerner dos clicks de menu
        nav_view.setNavigationItemSelectedListener(this)

        //Setando fragment home
        supportFragmentManager.beginTransaction().replace(R.id.frament_container, HomeFragment()).commit()
    }

    override fun onBackPressed() {
        if(drawer_layout.isDrawerOpen(GravityCompat.START))  {
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        else  {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var retorno: Boolean = false
        when(item.itemId){
            //Menu home
            R.id.nav_home ->{
                //Setando fragment home
                supportFragmentManager.beginTransaction().replace(R.id.frament_container, HomeFragment()).commit()
                retorno = true
            }
            //Menu sair
            R.id.nav_exit -> {
                finish()
                retorno = true
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return retorno
    }
}
