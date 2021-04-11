package com.example.peoplerootstack.utils

import android.content.Context
import android.view.View
import android.widget.Toast

object Utils {

    fun View.show(){ visibility = View.VISIBLE }
    fun View.hide(){ visibility = View.GONE }

    fun Context.longToast(message:String){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

}