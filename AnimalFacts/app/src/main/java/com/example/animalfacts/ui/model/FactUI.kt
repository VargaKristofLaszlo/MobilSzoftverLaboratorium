package com.example.animalfacts.ui.model

import com.example.animalfacts.domain.model.Fact
import java.util.Date

data class FactUi(
    val _id: String,
    val __v: Int,
    val text: String,
    val updatedAt: Date,
    val deleted: Boolean,
    val source: String,
    val sentCount: Int
)

fun Fact.asFactUi(): FactUi = FactUi(
    _id = _id,
    __v = __v,
    text =  text,
    updatedAt = updatedAt,
    deleted = deleted,
    source = source,
    sentCount = sentCount
)

fun FactUi.asFact(): Fact = Fact(
    _id = _id,
    __v = __v,
    text =  text,
    updatedAt = updatedAt,
    deleted = deleted,
    source = source,
    sentCount = sentCount
)