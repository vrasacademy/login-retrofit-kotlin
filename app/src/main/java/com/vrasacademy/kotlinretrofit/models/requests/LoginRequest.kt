package com.vrasacademy.kotlinretrofit.models.requests

data class LoginRequest(
    val email: String,
    val password: String
)