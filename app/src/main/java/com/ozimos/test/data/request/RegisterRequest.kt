package com.ozimos.test.data.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest(

    @field:SerializedName("password")
    val password: String,

    @field:SerializedName("username")
    val username: String,

    @field:SerializedName("email")
    val email: String
)
