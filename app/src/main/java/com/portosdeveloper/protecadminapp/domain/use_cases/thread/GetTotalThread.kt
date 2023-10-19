package com.portosdeveloper.protecadminapp.domain.use_cases.wadding

import com.portosdeveloper.protecadminapp.domain.repository.ThreadRepository
import javax.inject.Inject

class GetTotalThread @Inject constructor(private val repository: ThreadRepository) {

    suspend operator fun invoke(id: String, idCamp : String) = repository.getTotalThread(id,idCamp)

}