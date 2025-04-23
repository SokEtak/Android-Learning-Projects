package com.example.compose_mymealapp

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
):Parcelable

data class CategoryResponse(val categories: List<Category>)

