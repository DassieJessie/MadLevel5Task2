package com.example.gamebacklog.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "gameTable")
data class Game(

    @ColumnInfo(name = "title")
    var title : String,
    @ColumnInfo(name = "platform")
    var platform : String,

    @ColumnInfo(name = "releaseDate")
    var releaseDate: LocalDate,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)