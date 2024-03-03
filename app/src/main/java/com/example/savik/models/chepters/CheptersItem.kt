package com.example.savik.models.chepters

data class CheptersItem(
    val chapter_number: Int,
    val meaning: Meaning,
    val name: String,
    val summary: Summary,
    val translation: String,
    val transliteration: String,
    val verses_count: Int
)