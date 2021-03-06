package com.example.gamebacklog.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gamebacklog.converter.Converter
import com.example.gamebacklog.dao.GameDao
import com.example.gamebacklog.model.Game

@Database(entities = [Game::class], version = 2, exportSchema = false)
@TypeConverters(Converter::class)
abstract class GameDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao

    companion object {
        private const val DATABASE_NAME = "GAME_DATABASE"

        @Volatile
        private var gameDatabaseInstance: GameDatabase? = null

        fun getDatabase(context: Context): GameDatabase? {

            //make database
            if (gameDatabaseInstance == null) {
                synchronized(GameDatabase::class.java) {
                    if (gameDatabaseInstance == null) {
                        gameDatabaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            GameDatabase::class.java, DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }

            return gameDatabaseInstance
        }
    }
}