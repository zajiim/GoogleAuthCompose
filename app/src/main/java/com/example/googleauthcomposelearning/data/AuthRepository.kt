package com.example.googleauthcomposelearning.data

import com.example.googleauthcomposelearning.utils.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun signUp(email: String, password: String): Flow<Resource<AuthResult>>
    fun signIn(email: String, password: String): Flow<Resource<AuthResult>>
}