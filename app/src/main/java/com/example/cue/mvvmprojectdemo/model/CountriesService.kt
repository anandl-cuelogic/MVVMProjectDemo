package com.example.cue.mvvmprojectdemo.model

import com.example.cue.mvvmprojectdemo.di.DaggerApiComponet
import io.reactivex.Single
import javax.inject.Inject


class CountriesService {

   @Inject
   lateinit var api:CountriesApi

   init {
       DaggerApiComponet.create().inject(this)
   }

    fun getCountries(): Single<List<Country>> {
        return api.getCountries()
    }
}