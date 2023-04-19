package com.example.animalfacts.data.converters

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun Date.asString(): String = this.toString()

    @TypeConverter
    fun String.asDate(): Date = this.asDate()
}