package com.tasmiya.peopleinteractivedemoapp

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.tasmiya.peopleinteractivedemoapp.models.UserModel
import com.tasmiya.peopleinteractivedemoapp.util.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response


class MainViewModel @ViewModelInject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application), Observable {

//    companion object{
//        var msg = ""
//       //
//    }
//
//    init{
//        Log.d("msg",msg)
//    }


    //ROOM DATABASE
    val readUser : LiveData<List<UserEntity>> = repository.local.readUsers().asLiveData()
    // RETROFIT
    var userResponse: MutableLiveData<NetworkResult<UserModel>> = MutableLiveData()

    private fun insertUsers(userEntity: UserEntity)=
        viewModelScope.launch(Dispatchers.IO){
            repository.local.inserUser(userEntity)
        }

    private fun updateUsers(userEntity: UserEntity)=
        viewModelScope.launch(Dispatchers.IO){
            repository.local.update(userEntity)
        }

    fun getUsers() = viewModelScope.launch {
        getUsersSafeCall()
    }
    var tvMsg = ObservableField<String>()


    private suspend fun getUsersSafeCall() {
        userResponse.value =
            NetworkResult.Loading()
        if (hasInternetConnection()) {
            try {
                val response = repository.remote.getUsers()
                userResponse.value = handleFoodRecipesResponse(response)

                val userData = userResponse.value!!.data
                if(userData != null){
                    offlineCatchUsers(userData)
                }
            } catch (e: Exception) {
                userResponse.value =
                        NetworkResult.Error("Recipes not found.")
            }
        } else {
            userResponse.value =
                NetworkResult.Error("No Internet Connection.")
        }
    }

    private fun offlineCatchUsers(userData: UserModel) {
        val userEntity = UserEntity(userData,"Accepted")
        insertUsers(userEntity)

    }

     fun offlineCatchUsersAccept(userData: UserModel) {
        val userEntity = UserEntity(userData,"You Accepted")
         Log.d("updated","updated")
        updateUsers(userEntity)

    }

    val clicksListener = object : AdapterView.OnItemClickListener{
        override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        Log.d("ValueFrom","l")


        }

    }

    private fun handleFoodRecipesResponse(response: Response<UserModel>): NetworkResult<UserModel>? {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }
            response.code() == 402 -> {
                return NetworkResult.Error("API Key Limited.")
            }
            response.body()!!.results.isNullOrEmpty() -> {
                return NetworkResult.Error(" Error, not found.")
            }
            response.isSuccessful -> {
                val foodRecipes = response.body()
                return NetworkResult.Success(
                    foodRecipes!!
                )
            }
            else -> {
                return NetworkResult.Error(
                    response.message()
                )
            }
        }
    }

     fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

//    fun accept(){
//        val userEntity = UserEntity(userData,"You Accepted")
//        if (userData != null) {
//
//            Log.d("savedata","saveddata")
//            offlineCatchUsersAccept(userData)
//        }
//
//    }


}