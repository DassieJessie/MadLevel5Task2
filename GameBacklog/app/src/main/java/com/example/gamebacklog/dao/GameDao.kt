package com.example.gamebacklog.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.gamebacklog.model.Game

@Dao
interface GameDao {

    @Query("SELECT * FROM gameTable ORDER BY releaseDate ASC")
    fun getAllGames(): LiveData<List<Game>>

    @Query("DELETE FROM gameTable")
    suspend fun deleteAllGames()

    @Insert
    suspend fun insertGame(game: Game)

    @Delete
    suspend fun deleteGame(game:Game)
}