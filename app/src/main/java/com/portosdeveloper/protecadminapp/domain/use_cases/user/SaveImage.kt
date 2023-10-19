package com.portosdeveloper.protecadminapp.domain.use_cases.user

import com.portosdeveloper.protecadminapp.domain.repository.UsersRepository
import java.io.File
import javax.inject.Inject

class SaveImage @Inject constructor(private val repository: UsersRepository) {

    suspend operator fun  invoke(file : File) = repository.saveImage(file)

}