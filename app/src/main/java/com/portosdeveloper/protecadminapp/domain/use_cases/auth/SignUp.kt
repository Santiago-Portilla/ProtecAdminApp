package com.portosdeveloper.protecadminapp.domain.use_cases.auth

import com.portosdeveloper.protecadminapp.domain.model.User
import com.portosdeveloper.protecadminapp.domain.repository.AuthRepository
import javax.inject.Inject

class SignUp @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(user : User) = repository.signUp(user)
}