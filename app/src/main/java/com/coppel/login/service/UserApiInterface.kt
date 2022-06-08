package com.coppel.login.service

import com.coppel.login.entities.UserLoginRequest
import com.coppel.login.entities.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

internal interface UserApiInterface {
    @POST("login")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun loginUser(@Body params: UserLoginRequest):Call<StructureResponse<UserModel>>
}