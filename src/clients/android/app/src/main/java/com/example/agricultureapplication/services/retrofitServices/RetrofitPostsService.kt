package com.example.agricultureapplication.services.retrofitServices

import com.example.agricultureapplication.models.webapi.Post
import com.example.agricultureapplication.models.webapi.dto.PostCreateDto
import com.example.agricultureapplication.models.webapi.dto.PostUpdateDto
import retrofit2.Response
import retrofit2.http.*

interface RetrofitPostsService {
    @GET("api/plants/pager")
    suspend fun getPosts(@Query("skip") skip:Number,@Query("take") take:Number=5): Response<ArrayList<Post>>

    @GET("api/plants/{id}")
    suspend fun getPostById(@Path("id") id:Int): Response<Post>

    @POST("api/plants")
    suspend fun createPost(@Body post:PostCreateDto): Response<Post>

    @PUT("api/plants/{id}")
    suspend fun updatePost(@Path("id") id:Int,@Body post:PostUpdateDto): Response<Post>

    @DELETE("api/plants/{id}")
    suspend fun deletePost(@Path("id") id:Int): Response<Post>

}