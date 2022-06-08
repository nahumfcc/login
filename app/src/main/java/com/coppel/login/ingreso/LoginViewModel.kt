package com.coppel.login.ingreso

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coppel.login.entities.UserLoginRequest
import com.coppel.login.entities.UserModel
import com.coppel.login.service.RetrofitClient
import com.coppel.login.service.StructureResponse
import com.coppel.login.service.UserApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel:ViewModel() {
    lateinit var loggedUser: MutableLiveData<StructureResponse<UserModel>?>
    init {
        loggedUser = MutableLiveData()
    }
    fun getLoggedUserObserver():MutableLiveData<StructureResponse<UserModel>?>{
        return loggedUser
    }
    fun logginUser(user:UserLoginRequest)
    {
        var responseService = RetrofitClient.getRetrofitInstance().create(UserApiInterface::class.java)
        var call = responseService.loginUser(user)
        call.enqueue(object:Callback<StructureResponse<UserModel>>{
            override fun onFailure(call: Call<StructureResponse<UserModel>>, t: Throwable) {
                loggedUser.postValue(null)
            }

            override fun onResponse(
                call: Call<StructureResponse<UserModel>>,
                response: Response<StructureResponse<UserModel>>
            ) {
                if (response.isSuccessful)
                {
                    loggedUser.postValue(response.body())
                }
                else{
                    loggedUser.postValue(null)
                }
            }
        })
    }
}