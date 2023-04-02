package com.example.prostudio.pro_studio.domain.usecases

import com.example.prostudio.pro_studio.domain.repository.AuthenticationRepository
import javax.inject.Inject

class FirebaseSignIn @Inject constructor(
    private val repository: AuthenticationRepository
) {
    operator fun invoke(email: String, password: String) = repository.firebaseSignIn(email, password)
}