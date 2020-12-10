package com.example.gamebacklog.converter

import androidx.room.TypeConverter
import java.time.LocalDate

class Converter {

    @TypeConverter
    fun localDateToString(date: LocalDate): String {
        return date.toString()
    }

    @TypeConverter
    fun stringToLocalDate(dateString: String): LocalDate {
        return LocalDate.parse(dateString)
    }
}