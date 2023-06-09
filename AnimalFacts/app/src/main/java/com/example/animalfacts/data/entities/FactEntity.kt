package com.example.animalfacts.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.*

@Entity(tableName = "fact_table")
data class FactEntity(
    @PrimaryKey(autoGenerate = false) val _id: String,
    @ColumnInfo(name = "__v") val __v: Int,
    @ColumnInfo(name = "Text") val text: String,
    @ColumnInfo(name = "Deleted") val deleted: Boolean,
    @ColumnInfo(name = "SentCount") val sentCount: Int,
    @ColumnInfo(name = "Animal_type") val animal_type: String
)