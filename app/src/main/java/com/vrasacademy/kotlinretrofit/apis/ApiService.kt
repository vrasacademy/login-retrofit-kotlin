
package com.vrasacademy.kotlinretrofit.apis

import com.vrasacademy.kotlinretrofit.models.requests.LoginRequest
import com.vrasacademy.kotlinretrofit.models.responses.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("fe/api/v1/app/login")
    fun Login(@Body loginRequest: LoginRequest):Call<LoginResponse>

}