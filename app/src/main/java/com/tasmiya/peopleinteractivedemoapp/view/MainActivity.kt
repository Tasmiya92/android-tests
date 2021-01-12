package com.tasmiya.peopleinteractivedemoapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasmiya.peopleinteractivedemoapp.MainViewModel
import com.tasmiya.peopleinteractivedemoapp.R
import com.tasmiya.peopleinteractivedemoapp.RecyclerViewAdapter
import com.tasmiya.peopleinteractivedemoapp.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import androidx.lifecycle.observe
import com.tasmiya.peopleinteractivedemoapp.util.observeOnce
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel

    private  lateinit var  mAdapter : RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mAdapter = RecyclerViewAdapter(mainViewModel.clicksListener)
        setupRecyclerView()

//        if(mainViewModel.hasInternetConnection()){
//            requestApiData()
//        }else {
            readDatabase()
//        }

    }

    private fun readDatabase() {
        mainViewModel.readUser.observe(this, { database ->
            if(database.isNotEmpty()){
                Log.d("MainActivity","readDatabase called!")
                mAdapter.setData(database[0].userModel)
                hideShimmerEffect()
            }else{
                requestApiData()
            }
        })
    }

    // recyclerview with recipes
    private fun requestApiData() {
        Log.d("MainActivity","requestApiData called!")
        mainViewModel.getUsers()
        Log.d("Data", mainViewModel.getUsers().toString())
        mainViewModel.userResponse.observe(this, { response ->
            when(response){
                is NetworkResult.Success -> {
                     hideShimmerEffect()
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                     hideShimmerEffect()
                    loadDataFromCache()
                    Toast.makeText(
                            this,
                            response.message.toString(),
                            Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading ->{
                      showShimmerEffect()
                }
            }
        })
    }

    private fun setupRecyclerView() {
        rcvUser.adapter = mAdapter
        rcvUser.layoutManager = LinearLayoutManager(this)
        showShimmerEffect()
    }

    private fun loadDataFromCache(){

            mainViewModel.readUser.observe(this, { database ->
                if(database.isNotEmpty()){
                    mAdapter.setData(database[0].userModel)
                }
            })



    }

    private fun showShimmerEffect() {
        rcvUser.showShimmer()
    }

    private fun hideShimmerEffect() {
        rcvUser.hideShimmer()
    }

//    fun clickListner(){
//
//    }
}

