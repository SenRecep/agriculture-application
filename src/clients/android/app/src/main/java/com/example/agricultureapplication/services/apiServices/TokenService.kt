package com.example.agricultureapplication.services.apiServices

import android.content.Context
import com.example.agricultureapplication.consts.ApiConsts
import com.example.agricultureapplication.models.api.ApiResponse
import com.example.agricultureapplication.models.user.Introspec
import com.example.agricultureapplication.models.user.JwtToken
import com.example.agricultureapplication.services.retrofitServices.ApiClient
import com.example.agricultureapplication.services.retrofitServices.RetrofitTokenService
import com.example.agricultureapplication.utility.GlobalApp
import com.example.agricultureapplication.utility.HelperService
import com.google.gson.Gson

class TokenService {
    companion object {
        private var retrofitService =
            ApiClient.buildService(ApiConsts.webApiBaseUrl, RetrofitTokenService::class.java, true);

        suspend fun checkToken(): ApiResponse<Unit> {
            try {
                var preference = GlobalApp.getAppContext()
                    .getSharedPreferences("api_token", Context.MODE_PRIVATE);
                var tokenString: String? =
                    preference.getString("token", null) ?: return ApiResponse(false);
                var token: JwtToken = Gson().fromJson(tokenString, JwtToken::class.java);
                var response = retrofitService.checkToken(token.AccessToken);
                if (!response.isSuccessful) return ApiResponse(false);
                var introspec = response.body() as Introspec;
                return ApiResponse(introspec.Active);
            } catch (ex: Exception) {
                return HelperService.handleException(ex);
            }
        }
    }
}