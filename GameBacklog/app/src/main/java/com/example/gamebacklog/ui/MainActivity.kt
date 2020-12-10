package com.example.gamebacklog.ui

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.gamebacklog.R
import com.example.gamebacklog.model.Game
import com.example.gamebacklog.viewmodel.GameViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add_game.*
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        navController = findNavController(R.id.nav_host_fragment)

        fab.setOnClickListener {
            navController.navigate(
                R.id.action_gameBacklogFragment_to_addGameFragment
            )
        }

//        fabYeet.setOnClickListener{
//            //onAddGame()
//            navController.navigate(
//                R.id.gameBacklogFragment
//            )
//        }

        fabToggler()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        navController.addOnDestinationChangedListener { _,       destination, _ ->
            menu.findItem(R.id.btnBin)?.isVisible = destination.id !in arrayOf(R.id.addGameFragment)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.btnBin -> {
                viewModel.deleteAllGames()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun fabToggler() {

        navController.addOnDestinationChangedListener { _,       destination, _ ->
            if (destination.id in arrayOf(R.id.addGameFragment)) {
                fab.hide()

            } else {
                fab.show()
            }
        }
    }

//    private fun onAddGame(){
//        val title = etTitle.text.toString()
//        val platform = etPlatform.text.toString()
//        val date = LocalDate.of(etYear.text.toString().toInt(), etMonth.text.toString().toInt(), etDay.text.toString().toInt())
//
//        //update variabele maar 1x
//        Log.d("GAME", "$title $platform $date")
//        viewModel.insertGame(Game(title, platform, date))
//    }
}