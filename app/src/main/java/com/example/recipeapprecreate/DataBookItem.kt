package com.example.recipeapprecreate

data class DataBookItem(
    val author: String,
    val ingredients: String,
    val instructions: String,
    val title: String,
    val pk: Int?=0
)