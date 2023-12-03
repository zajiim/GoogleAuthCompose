package com.example.googleauthcomposelearning.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.googleauthcomposelearning.data.AuthRepository
import com.example.googleauthcomposelearning.utils.Resource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    val _signUpState = Channel<SignUpState>()
    val signUpState = _signUpState.receiveAsFlow()

    fun signUp(email: String, password: String) = viewModelScope.launch {
        authRepository.signUp(email, password)
            .collect {result ->
                when(result) {
                    is Resource.Error -> {
                        _signUpState.send(SignUpState(isError = result.message.toString()))
                    }
                    is Resource.Loading -> {
                        _signUpState.send(SignUpState(isLoading = true))
                    }
                    is Resource.Success -> {
                        _signUpState.send(SignUpState(isSuccess = "SignUp Success"))
                    }
                }
            }
    }
}