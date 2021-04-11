package com.example.peoplerootstack.api


import android.util.Log
import com.example.peoplerootstack.model.ErrorResponse
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.Response

object ApiError {

    private fun parseError(response: JsonObject): ErrorResponse {
        return try {
            Gson().fromJson(response,ErrorResponse::class.java)
        } catch (t: Throwable) {
            Log.e("SimpleErrorResponse", t.message.toString())
            ErrorResponse("Error: "+t.message.toString())
        }

    }

    fun validateError(response: Response<*>?,error:String) : String{
        return try {
            val json = Gson().fromJson(response!!.errorBody()!!.string(), JsonObject::class.java)
            parseError(json).error?:"Error"
        }catch (t:Throwable){
            "$error: ${t.message.toString()}"
        }
    }
}