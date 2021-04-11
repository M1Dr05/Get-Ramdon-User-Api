package com.example.peoplerootstack.model

import com.google.gson.annotations.SerializedName

data class Person(
    @SerializedName("results")
    var results:ArrayList<Results> = arrayListOf(),
    @SerializedName("info")
    var info:Info
)

data class Results(
    @SerializedName("name")
    var name:Name,
    @SerializedName("location")
    var location:Location,
    @SerializedName("picture")
    var picture:Picture
)

data class Name(
    @SerializedName("title")
    var title:String,
    @SerializedName("first")
    var first:String,
    @SerializedName("last")
    var last:String
)

data class Location(
    @SerializedName("street")
    var street: Street
)

data class Street(
    @SerializedName("number")
    var number:Int,
    @SerializedName("name")
    var name:String
)

data class Picture(
    @SerializedName("medium")
    var medium:String
)

data class Info(
    @SerializedName("seed")
    var seed : String,
    @SerializedName("results")
    var results:Int,
    @SerializedName("page")
    var page:Int
)
