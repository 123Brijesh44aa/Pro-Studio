package com.example.prostudio.pro_studio.domain.usecases

import com.example.prostudio.pro_studio.domain.repository.AuthenticationRepository
import javax.inject.Inject

class FirebaseSignOut @Inject constructor(
    private val repository: AuthenticationRepository
) {
    operator fun invoke() = repository.firebaseSignOut()
}