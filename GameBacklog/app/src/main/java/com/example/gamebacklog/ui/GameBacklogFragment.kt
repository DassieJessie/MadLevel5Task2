package com.example.gamebacklog.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamebacklog.R
import com.example.gamebacklog.model.Game
import com.example.gamebacklog.viewmodel.GameViewModel
import kotlinx.android.synthetic.main.fragment_game_backlog.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameBacklogFragment : Fragment() {

    private val games = arrayListOf<Game>()
    private val gameAdapter = GameAdapter(games)

    private val viewModel : GameViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_backlog, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRV()
    }

    private fun initRV(){
        rvGames.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvGames.adapter = gameAdapter

        rvGames.addItemDecoration(
            DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL)
        )

        createItemTouchHelper().attachToRecyclerView(rvGames)

        observeAddGame()
    }

    private fun observeAddGame(){
        viewModel.games.observe(viewLifecycleOwner, Observer { games ->
            this.games.clear()
            this.games.addAll(games)
            gameAdapter.notifyDataSetChanged()

        })
    }

    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                val gameToDelete = games[position]
                viewModel.deleteGame(gameToDelete)
            }
        }

        return ItemTouchHelper(callback)
    }


}