package com.example.peoplerootstack.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
        @SerializedName("error")
        val error: String?
)