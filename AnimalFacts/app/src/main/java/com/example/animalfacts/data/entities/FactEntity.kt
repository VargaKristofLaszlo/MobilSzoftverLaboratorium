package com.example.animalfacts.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "fact_table")
data class FactEntity(
    @PrimaryKey(autoGenerate = false) val _id: String,
    val __v: Int,
    val text: String,
    val updatedAt: Date,
    val deleted: Boolean,
    val source: String,
    val sentCount: Int
)