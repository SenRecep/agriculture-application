package com.example.agricultureapplication.services.apiServices

import com.example.agricultureapplication.consts.ApiConsts
import com.example.agricultureapplication.models.api.ApiResponse
import com.example.agricultureapplication.models.api.Pager
import com.example.agricultureapplication.models.webapi.Post
import com.example.agricultureapplication.models.webapi.dto.PostCreateDto
import com.example.agricultureapplication.models.webapi.dto.PostUpdateDto
import com.example.agricultureapplication.services.retrofitServices.ApiClient
import com.example.agricultureapplication.services.retrofitServices.RetrofitPostsService
import com.example.agricultureapplication.utility.HelperService

class PostsService {
    companion object{
        private var retrofitService =
            ApiClient.buildService(ApiConsts.webApiBaseUrl, RetrofitPostsService::class.java, true);
        suspend fun getPosts(pager: Pager):ApiResponse<ArrayList<Post>> {
            try {
                var response = retrofitService.getPosts(pager.skip,pager.take);
                if (!response.isSuccessful) return HelperService.handleApiError(response);
                var posts = response.body()!!;
                return ApiResponse(true, posts);
            } catch (ex: Exception) {
                return HelperService.handleException(ex);
            }
        }
        suspend fun getPostById(id: Int):ApiResponse<Post> {
            try {
                var response = retrofitService.getPostById(id);
                if (!response.isSuccessful) return HelperService.handleApiError(response);
                var post = response.body()!!;
                return ApiResponse(true, post);
            } catch (ex: Exception) {
                return HelperService.handleException(ex);
            }
        }

        suspend fun createPost(createDto: PostCreateDto):ApiResponse<Post> {
            try {
                var response = retrofitService.createPost(createDto);
                if (!response.isSuccessful) return HelperService.handleApiError(response);
                var post = response.body()!!;
                return ApiResponse(true, post);
            } catch (ex: Exception) {
                return HelperService.handleException(ex);
            }
        }

        suspend fun updatePost(id:Int,updateDto: PostUpdateDto):ApiResponse<Post> {
            try {
                var response = retrofitService.updatePost(id,updateDto);
                if (!response.isSuccessful) return HelperService.handleApiError(response);
                var post = response.body()!!;
                return ApiResponse(true, post);
            } catch (ex: Exception) {
                return HelperService.handleException(ex);
            }
        }

        suspend fun deletePost(id:Int):ApiResponse<Post> {
            try {
                var response = retrofitService.deletePost(id);
                if (!response.isSuccessful) return HelperService.handleApiError(response);
                var post = response.body()!!;
                return ApiResponse(true, post);
            } catch (ex: Exception) {
                return HelperService.handleException(ex);
            }
        }
    }

}