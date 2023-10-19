package com.portosdeveloper.protecadminapp.domain.use_cases.cloth

import com.portosdeveloper.protecadminapp.domain.model.Cloth
import com.portosdeveloper.protecadminapp.domain.repository.ClothRepository
import javax.inject.Inject

class CreateCloth @Inject constructor(private val repository: ClothRepository) {

    suspend operator fun invoke(cloth : Cloth) = repository.createCloth(cloth)

}