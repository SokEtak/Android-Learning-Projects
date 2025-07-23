package com.example.retrofit

import com.google.gson.annotations.SerializedName

data class Posts(
    val userId : Int,
    val id : Int,
    val title : String?,
    @SerializedName("body") //in the case that we don't want original name from apiService
    val subtitle : String) {

}