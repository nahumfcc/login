package com.coppel.login.entities

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("IsValidUser") var isValidUser: Boolean,
    @SerializedName("Nombre") var nombre: String,
    @SerializedName("ApellidoPaterno") var apellidoPaterno: String,
    @SerializedName("ApellidoMaterno") var apellidoMaterno: String,
    @SerializedName("idRol") var idRol: Int,
    @SerializedName("usuario") var usuario: String
)
