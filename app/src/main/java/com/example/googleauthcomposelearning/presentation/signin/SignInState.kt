package com.example.googleauthcomposelearning.presentation.signin

data class SignInState(
    val isLoading: Boolean = false,
    val isSuccess : String ?= "",
    val isError: String ?= ""
)
