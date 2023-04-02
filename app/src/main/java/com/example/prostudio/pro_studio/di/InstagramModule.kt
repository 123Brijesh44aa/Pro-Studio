package com.example.prostudio.pro_studio.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.example.prostudio.pro_studio.data.repository.AuthenticationRepositoryImpl
import com.example.prostudio.pro_studio.domain.repository.AuthenticationRepository
import com.example.prostudio.pro_studio.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InstagramModule {

    @Singleton
    @Provides
    fun providesFirebaseAuthentication(): FirebaseAuth{
        return FirebaseAuth.getInstance();
    }

    @Singleton
    @Provides
    fun providesFirebaseFirestore(): FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun providesFirebaseStorage(): FirebaseStorage{
        return FirebaseStorage.getInstance()
    }

    @Singleton
    @Provides
    fun providesAuthenticationRepository(auth: FirebaseAuth, firestore: FirebaseFirestore): AuthenticationRepository {
        return AuthenticationRepositoryImpl(auth = auth, firestore = firestore)
    }

    @Singleton
    @Provides
    fun providesAuthUseCases(repository: AuthenticationRepository) = AuthenticationUseCases(
        isUserAuthenticated = IsUserAuthenticated(repository = repository),
        firebaseAuthState = FirebaseAuthState(repository = repository),
        firebaseSignOut = FirebaseSignOut(repository = repository),
        firebaseSignIn = FirebaseSignIn(repository = repository),
        firebaseSignUp = FirebaseSignUp(repository = repository)
    )
}