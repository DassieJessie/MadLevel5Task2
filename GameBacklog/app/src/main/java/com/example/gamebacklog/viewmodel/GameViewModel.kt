package com.example.gamebacklog.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.gamebacklog.model.Game
import com.example.gamebacklog.repository.GameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GameViewModel(application: Application): AndroidViewModel(application) {

    private val repository: GameRepository = GameRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    val games: LiveData<List<Game>> = repository.getAllGames()

    fun insertGame(game:Game){
        mainScope.launch {
            withContext(Dispatchers.IO){
                repository.insertGame(game)
            }
        }
    }

    fun deleteGame(game:Game){
        mainScope.launch {
            withContext(Dispatchers.IO){
                repository.deleteGame(game)
            }
        }
    }

    fun deleteAllGames(){
        mainScope.launch {
            withContext(Dispatchers.IO){
                repository.deleteAllGames()
            }
        }
    }


}