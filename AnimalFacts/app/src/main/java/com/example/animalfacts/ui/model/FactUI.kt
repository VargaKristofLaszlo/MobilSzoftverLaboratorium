package com.example.animalfacts.ui.model

import java.util.Date

data class Fact(
    val _id: String,
    val __v: Int,
    val text: String,
    val updatedAt: Date,
    val deleted: Boolean,
    val source: String,
    val sentCount: Int
)

fun FactEntity.asFact(): Fact = Fact(
    _id = _id,
    __v = __v,
    text =  text,
    updatedAt = updatedAt,
    deleted = deleted,
    source = source,
    sentCount = sentCount
)

fun Fact.asFactEntity(): FactEntity = FactEntity(
    _id = _id,
    __v = __v,
    text =  text,
    updatedAt = updatedAt,
    deleted = deleted,
    source = source,
    sentCount = sentCount
)