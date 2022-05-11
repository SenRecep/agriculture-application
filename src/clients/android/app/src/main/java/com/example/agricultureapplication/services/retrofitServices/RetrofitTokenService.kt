package com.example.agricultureapplication.services.retrofitServices

import com.example.agricultureapplication.models.user.Introspec
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitTokenService {
    @FormUrlEncoded
    @POST("api/auth/verify")
    suspend fun checkToken(
        @Field("access_token") token: String
    ): Response<Introspec>
}