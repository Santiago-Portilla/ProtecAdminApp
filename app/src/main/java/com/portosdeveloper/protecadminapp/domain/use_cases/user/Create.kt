package com.portosdeveloper.protecadminapp.domain.use_cases.user

import com.portosdeveloper.protecadminapp.domain.model.User
import com.portosdeveloper.protecadminapp.domain.repository.UsersRepository
import javax.inject.Inject

class Create @Inject constructor(private val repository: UsersRepository) {

    suspend operator fun invoke(user : User) = repository.create(user)

}