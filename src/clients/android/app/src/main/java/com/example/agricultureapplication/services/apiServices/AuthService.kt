package com.example.agricultureapplication.services.apiServices

import com.example.agricultureapplication.consts.ApiConsts
import com.example.agricultureapplication.models.api.ApiResponse
import com.example.agricultureapplication.models.user.JwtToken
import com.example.agricultureapplication.models.user.UserSignIn
import com.example.agricultureapplication.models.user.UserSignUp
import com.example.agricultureapplication.services.retrofitServices.ApiClient
import com.example.agricultureapplication.services.retrofitServices.RetrofitAuthService
import com.example.agricultureapplication.utility.HelperService

class AuthService {
    companion object {
        private var retrofitService =
            ApiClient.buildService(ApiConsts.webApiBaseUrl, RetrofitAuthService::class.java, false);

        suspend fun signUp(userSignUp: UserSignUp): ApiResponse<Unit> {
            try {
                var response = retrofitService.signUp(userSignUp);
                if (!response.isSuccessful) return HelperService.handleApiError(response);
                return ApiResponse(true);
            } catch (ex: Exception) {
                return HelperService.handleException(ex);
            }
        }

        suspend fun signIn(userSignIn: UserSignIn): ApiResponse<Unit> {
            try {
                var response = retrofitService.signIn(userSignIn);
                if (!response.isSuccessful) return HelperService.handleApiError(response);

                var token = response.body() as JwtToken;
                HelperService.saveTokenSharedPreference(token);
                return ApiResponse(true);
            } catch (ex: Exception) {
                return HelperService.handleException(ex);
            }
        }

    }
}