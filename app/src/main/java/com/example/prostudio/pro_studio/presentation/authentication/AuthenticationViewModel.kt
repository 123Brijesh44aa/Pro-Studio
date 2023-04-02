package com.example.prostudio.pro_studio.presentation.authentication

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prostudio.pro_studio.domain.usecases.AuthenticationUseCases
import com.example.prostudio.pro_studio.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val authenticationUseCases: AuthenticationUseCases
): ViewModel() {

    val isUserAuthenticated get() = authenticationUseCases.isUserAuthenticated()

    private val _signInState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signInState: State<Response<Boolean>> = _signInState

    private val _signUpState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signUpState: State<Response<Boolean>> = _signUpState

    private val _signOutState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signOutState: State<Response<Boolean>> = _signOutState

    private val _firebaseAuthState = mutableStateOf<Boolean>(false)
    val firebaseAuthState: State<Boolean> = _firebaseAuthState

    fun signIn(email: String, password: String){
        viewModelScope.launch {
            authenticationUseCases.firebaseSignIn(email,password).collect{
                _signInState.value = it
            }
        }
    }

    fun signUp(email: String, password: String, username: String){
        viewModelScope.launch {
            authenticationUseCases.firebaseSignUp(email,password,username).collect{
                _signUpState.value = it
            }
        }
    }

    fun signOut(){
        viewModelScope.launch {
            authenticationUseCases.firebaseSignOut().collect{
                _signOutState.value = it
                if (it == Response.Success(true)){
                    _signInState.value = Response.Success(false)
                }
            }
        }
    }

    fun getFirebaseAuthState(){
        viewModelScope.launch {
            authenticationUseCases.firebaseAuthState().collect{
                _firebaseAuthState.value = it
            }
        }
    }
}