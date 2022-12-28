package com.ozimos.test.data

import com.ozimos.test.data.request.LoginRequest
import com.ozimos.test.data.request.RegisterRequest
import com.ozimos.test.data.response.BaseResponse
import com.ozimos.test.data.response.CheckListResponse
import com.ozimos.test.data.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("register")
    suspend fun register(
        @Body body: RegisterRequest
    ): Response<BaseResponse<Nothing>>

    @POST("login")
    suspend fun login(
        @Body body: LoginRequest
    ): Response<BaseResponse<LoginResponse>>

    @GET("checklist")
    suspend fun getChcecklist(
    ): Response<BaseResponse<List<CheckListResponse>>>


}