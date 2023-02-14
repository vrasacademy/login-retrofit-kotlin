package com.vrasacademy.kotlinretrofit.models.responses

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("code")
    var code: Int,
    @SerializedName("status")
    var status: String,
    @SerializedName("message")
    var message: String,
    @SerializedName("response")
    var `response`: Response
) {
    data class Response(
        @SerializedName("user")
        var `user`: User,
        @SerializedName("authorization")
        var authorization: Authorization
    ){
        data class User(
            @SerializedName("id")
            var id: Int,
            @SerializedName("name")
            var name: String,
            @SerializedName("email")
            var email: String,
            @SerializedName("profile_img")
            var profile_img: String
        )
        data class Authorization(
            @SerializedName("token")
            var token: String,
            @SerializedName("type")
            var type: String,
            @SerializedName("expires_in")
            var expires_in: Int
        )
    }
}