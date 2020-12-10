package com.example.gamebacklog.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.gamebacklog.R
import com.example.gamebacklog.model.Game
import com.example.gamebacklog.viewmodel.GameViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add_game.*
import kotlinx.android.synthetic.main.item_game.*
import java.time.LocalDate

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddGameFragment : Fragment() {

    private val viewModel: GameViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_game, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabYeet.setOnClickListener{
            onAddGame()
        }
    }

    private fun onAddGame(){
        val title = etTitle.text.toString()
        val platform = etPlatform.text.toString()
        val date = LocalDate.of(etYear.text.toString().toInt(), etMonth.text.toString().toInt(), etDay.text.toString().toInt())

        Log.d("GAME", "$title $platform $date")
        viewModel.insertGame(Game(title, platform, date))
    }
}