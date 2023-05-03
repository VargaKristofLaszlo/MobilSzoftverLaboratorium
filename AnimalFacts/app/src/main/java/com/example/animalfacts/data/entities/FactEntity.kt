package com.example.animalfacts.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "fact_table")
data class FactEntity(
    @PrimaryKey(autoGenerate = false) val _id: String,
    @ColumnInfo(name = "__v") val __v: Int,
    @ColumnInfo(name = "Text") val text: String,
    @ColumnInfo(name = "Update_at") val updatedAt: Date,
    @ColumnInfo(name = "Deleted") val deleted: Boolean,
    @ColumnInfo(name = "SentCount") val sentCount: Int
)