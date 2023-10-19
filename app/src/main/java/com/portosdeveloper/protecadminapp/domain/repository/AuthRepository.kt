package com.portosdeveloper.protecadminapp.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.model.User

interface AuthRepository {

    val currentUser: FirebaseUser?
    suspend fun login(email: String, password : String): Response<FirebaseUser>
    suspend fun signUp(user: User): Response<FirebaseUser>
    fun logOut()


}