package pe.edu.ulima.pm.renta

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import pe.edu.ulima.pm.renta.databinding.ActivityMainBinding
import pe.edu.ulima.pm.renta.model.Inmueble
import pe.edu.ulima.pm.renta.ui.inmueble.InmuebleFragment

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main2)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_profile
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setNavigationItemSelectedListener { menuItem ->
            var nav_profileIsSelect = false
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    Toast.makeText(this, "Clicked item one", Toast.LENGTH_SHORT).show()
                    val fragment = InmuebleFragment()
                    val ft = supportFragmentManager.beginTransaction()
                    ft.replace(R.id.nav_host_fragment_content_main2,fragment)
                    ft.commit()
                    true
                }
                R.id.nav_gallery -> {
                    Toast.makeText(this, "Clicked item two", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_slideshow -> {
                    Toast.makeText(this, "Clicked item tree", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    this.startActivity(intent)
                    nav_profileIsSelect = true
                    true
                }
                else -> {
                    true
                }
            }
            if (nav_profileIsSelect){
                menuItem.isChecked = true
            }
            drawerLayout.closeDrawers()
            true
        }

        updateUI()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_activity, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main2)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            signOut()
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    private fun signOut() {
        auth.signOut()
        val intent = Intent(this, SignInActivity::class.java)
        this.startActivity(intent)
    }

    private fun updateUI() {
        val user = auth.currentUser
        if (user != null) {
            val navView: NavigationView = binding.navView
            val headerView = navView.getHeaderView(0)
            headerView.findViewById<TextView>(R.id.textViewUser).text = user.displayName
            headerView.findViewById<TextView>(R.id.textViewEmail).text = user.email
            Glide.with(this).load(user.photoUrl)
                .centerCrop()
                .placeholder(R.drawable.profile_photo)
                .into(headerView.findViewById(R.id.imageViewPhoto))
        }
    }


}