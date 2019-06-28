package com.example.cue.mvvmprojectdemo.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.cue.mvvmprojectdemo.R
import com.example.cue.mvvmprojectdemo.viewModel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

   lateinit var viewModel:ListViewModel
   private val countryListAdapter = CountryListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        countryList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countryListAdapter
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }
       observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.countries.observe(this, Observer {
            it?.let {
                countryList.visibility = View.VISIBLE
                countryListAdapter.updateCountries(it) }
        })

        viewModel.countryLoadError.observe(this, Observer {
            isError -> isError?.let { list_error.visibility = if(isError) View.VISIBLE else View.GONE }
        })

        viewModel.loading.observe(this, Observer {
            isLoading -> isLoading?.let {
            loading_view.visibility = if(it)View.VISIBLE else View.GONE
            if(it){
                list_error.visibility = View.GONE
                countryList.visibility = View.GONE
            }
        }
        })
    }
}
