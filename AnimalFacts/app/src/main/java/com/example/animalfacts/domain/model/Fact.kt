package com.example.animalfacts.domain.model

import com.example.animalfacts.data.entities.FactEntity
import java.time.LocalDate
import java.util.*

data class Fact(
    val _id: String,
    val __v: Int,
    val text: String,
    val deleted: Boolean,
    val sentCount: Int,
    val animal_type: String
)

fun FactEntity.asFact(): Fact = Fact(
    _id = _id,
    __v = __v,
    text =  text,
    deleted = deleted,
    sentCount = sentCount,
    animal_type = animal_type
)

fun Fact.asFactEntity(): FactEntity = FactEntity(
    _id = _id,
    __v = __v,
    text =  text,
    deleted = deleted,
    sentCount = sentCount,
    animal_type = animal_type
)