package com.example.peoplerootstack.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.peoplerootstack.api.ApiError
import com.example.peoplerootstack.api.ApiService
import com.example.peoplerootstack.model.Person
import com.example.peoplerootstack.model.Error
import com.example.peoplerootstack.model.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainService {

    fun getPersons(page:Int) : LiveData<Resource<Person>> {
        val data = MutableLiveData<Resource<Person>>()
        ApiService.apiService?.getPerson(page)?.enqueue(object : Callback<Person> {
            override fun onResponse(call: Call<Person>, response: Response<Person>) {
                if (response.isSuccessful && response.body()!=null)
                    data.value = Resource.success(response.body())
                else data.value = Resource.error(Error(ApiError.validateError(response,"error"),response.code()))
            }

            override fun onFailure(call: Call<Person>, t: Throwable) {
                data.value = Resource.error(Error(t.message.toString(), 500 ))
            }

        })

        return data
    }

}