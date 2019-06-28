package com.example.cue.mvvmprojectdemo.di

import com.example.cue.mvvmprojectdemo.model.CountriesService
import com.example.cue.mvvmprojectdemo.viewModel.ListViewModel
import dagger.Component

@Component(modules =[ApiModule::class])
interface ApiComponet {
    fun inject(service: CountriesService)
    fun inject(viewModel: ListViewModel)
}