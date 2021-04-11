package com.example.peoplerootstack.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.peoplerootstack.model.Person
import com.example.peoplerootstack.model.Resource

class MainViewModel : ViewModel() {

    private val mainService = MainService()
    var titleItem : String = ""
    var page:Int = 1

    fun getPersons() : LiveData<Resource<Person>> {
        val personTemp = mainService.getPersons(page)
        return Transformations.map(personTemp){
            if (it.status == Resource.Status.SUCCESS && it.data != null) {
                page = it.data.info.page + 1
            }
            it
        }
    }

}