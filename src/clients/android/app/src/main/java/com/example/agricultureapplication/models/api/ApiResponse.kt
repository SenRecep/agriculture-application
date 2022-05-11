package com.example.agricultureapplication.models.api

data class ApiResponse<T>(var isSuccessful: Boolean, var data: T? = null, var error: ApiError? = null)