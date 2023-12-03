package com.example.googleauthcomposelearning.data

import com.example.googleauthcomposelearning.utils.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override fun signUp(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password)
                .await()
            emit(Resource.Success(result))
        }.catch { err ->
            emit(Resource.Error(err.message.toString()))
        }
    }

    override fun signIn(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.signInWithEmailAndPassword(email, password)
                .await()
            emit(Resource.Success(result))
        }.catch { err ->
            emit(Resource.Error(err.message.toString()))
        }
    }
}