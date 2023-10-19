package com.portosdeveloper.protecadminapp.domain.use_cases.auth

import com.portosdeveloper.protecadminapp.domain.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUser @Inject constructor(private val repository: AuthRepository) {

    operator fun invoke() = repository.currentUser

}