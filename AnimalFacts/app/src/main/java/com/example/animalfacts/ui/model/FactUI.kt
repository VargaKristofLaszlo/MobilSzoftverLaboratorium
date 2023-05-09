package com.example.animalfacts.ui.model

import com.example.animalfacts.domain.model.Fact
import java.time.LocalDate
import java.util.Date

data class FactUi(
    val _id: String,
    val __v: Int,
    val text: String,
    val deleted: Boolean,
    val sentCount: Int
)

fun Fact.asFactUi(): FactUi = FactUi(
    _id = _id,
    __v = __v,
    text =  text,
    deleted = deleted,
    sentCount = sentCount
)

fun FactUi.asFact(): Fact = Fact(
    _id = _id,
    __v = __v,
    text =  text,
    deleted = deleted,
    sentCount = sentCount
)