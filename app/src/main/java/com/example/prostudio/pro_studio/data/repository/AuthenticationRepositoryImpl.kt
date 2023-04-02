package com.example.prostudio.pro_studio.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.example.prostudio.pro_studio.domain.model.User
import com.example.prostudio.pro_studio.domain.repository.AuthenticationRepository
import com.example.prostudio.pro_studio.util.Constants
import com.example.prostudio.pro_studio.util.Response
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
): AuthenticationRepository {

    var operationSuccessful: Boolean = false;

    override fun isUserAuthenticatedInFirebase(): Boolean {
        return auth.currentUser != null   // if not null return true else false
    }


    /*
WHAT IS FIREBASE AUTH LISTENER
AuthStateListener is called when there is a change in the authentication state.

OnAuthStateChanged gets invoked in the UI thread on changes in the authentication state:

Right after the listener has been registered
When a user is signed in
When the current user is signed out
When the current user changes
*/
//    CallbackFlow is used to transform any callback based api into Flow
    override fun getFirebaseAuthState(): Flow<Boolean> {
        return callbackFlow{
            val authStateListener = FirebaseAuth.AuthStateListener{ // AuthStateListener is callback and callback flow will convert it into  Flow
                trySend(auth.currentUser == null)
            }
            auth.addAuthStateListener ( authStateListener)
            awaitClose{
                auth.removeAuthStateListener(authStateListener)
            }
        }
    }

    override fun firebaseSignIn(email: String, password: String): Flow<Response<Boolean>> = flow{
        operationSuccessful = false
        try {
          emit(Response.Loading)
          auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
              operationSuccessful = true
          }.await()

            emit(Response.Success(operationSuccessful))
        }
        catch (e: Exception){
            emit(Response.Error(e.localizedMessage ?: "An Unexpected Error"))
        }
    }

    override fun firebaseSignOut(): Flow<Response<Boolean>> = flow{
        try {
            emit(Response.Loading)
            auth.signOut()
            emit(Response.Success(true))
        }
        catch (e: Exception){
            emit(Response.Error(e.localizedMessage ?: "An Unexpected Error"))
        }
    }

    override fun firebaseSignUp(email: String, password: String, username: String): Flow<Response<Boolean>> = flow{
        operationSuccessful = false
        try {
            emit(Response.Loading)
            auth.createUserWithEmailAndPassword(email, password). addOnSuccessListener {
                operationSuccessful = true
            }.await()
            if (operationSuccessful){   // if operation is successful then store this data into firebaseFirestore
                val userid = auth.currentUser?.uid!!
                val obj = User(username = username, userid = userid, password = password, email = email)
                firestore.collection(Constants.COLLECTION_NAME_USERS).document(userid).set(obj).addOnSuccessListener {

                }.await()
                emit(Response.Success(operationSuccessful))
            }
            else{  // if operation is not successful
                Response.Success(operationSuccessful)
            }
        }
        catch (e: Exception){
            emit(Response.Error(e.localizedMessage ?: "An Unexpected Error"))
        }
    }
}