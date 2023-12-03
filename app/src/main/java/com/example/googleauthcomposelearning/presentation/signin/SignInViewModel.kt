package com.example.googleauthcomposelearning.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.googleauthcomposelearning.data.AuthRepository
import com.example.googleauthcomposelearning.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    val _signInState = Channel<SignInState>()
    val signInState = _signInState.receiveAsFlow()

    fun signInUser(email: String, password: String) = viewModelScope.launch {
        authRepository.signIn(email, password)
            .collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _signInState.send(SignInState(isError = result.message.toString()))
                    }
                    is Resource.Loading -> {
                        _signInState.send(SignInState(isLoading = true))
                    }

                    is Resource.Success -> {
                        _signInState.send(SignInState(isSuccess = "Sign in Success"))
                    }
                }
            }
    }
}