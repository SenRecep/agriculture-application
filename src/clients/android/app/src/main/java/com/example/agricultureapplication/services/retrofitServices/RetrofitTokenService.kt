package com.example.agricultureapplication.services.retrofitServices

import com.example.agricultureapplication.models.user.Introspec
import retrofit2.Response
import retrofit2.http.*

interface RetrofitTokenService {
    @FormUrlEncoded
    @POST("api/auth/verify")
    suspend fun checkToken(
        @Field("access_token") token: String
    ): Response<Introspec>

    @GET("api/users/isAdmin")
    suspend fun isAdmin(): Response<Boolean>
}