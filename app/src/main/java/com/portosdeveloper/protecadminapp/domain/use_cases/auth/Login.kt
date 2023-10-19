package com.portosdeveloper.protecadminapp.domain.use_cases.auth

import com.portosdeveloper.protecadminapp.domain.repository.AuthRepository
import javax.inject.Inject

class Login @Inject constructor(private val repository: AuthRepository) {

    suspend operator fun invoke(email: String, password : String) = repository.login(email, password)

}