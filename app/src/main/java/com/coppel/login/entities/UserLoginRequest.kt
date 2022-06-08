package com.coppel.login.entities

import com.google.gson.annotations.SerializedName

data class UserLoginRequest(@SerializedName("usuario") val usuario:String?,
                            @SerializedName("contraseña") val contraseña:String?)
