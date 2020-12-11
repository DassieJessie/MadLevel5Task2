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

    private val repository = GameRepository(application.applicationContext)
    private val ioScope = CoroutineScope(Dispatchers.IO)

    val games: LiveData<List<Game>> = repository.getAllGames()

    fun insertGame(game:Game){
       ioScope.launch {
           repository.insertGame(game)
       }
    }

    fun deleteGame(game:Game){
        ioScope.launch {
            repository.deleteGame(game)
        }
    }

    fun deleteAllGames(){
       ioScope.launch {
           repository.deleteAllGames()
       }
    }


}