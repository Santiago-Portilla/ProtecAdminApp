package com.portosdeveloper.protecadminapp.domain.use_cases.cloth

import com.portosdeveloper.protecadminapp.domain.model.Cloth
import com.portosdeveloper.protecadminapp.domain.repository.ClothRepository
import javax.inject.Inject

class UpdateCloth @Inject constructor(private val repository: ClothRepository) {

    suspend operator fun invoke(cloth: Cloth)= repository.updateCloth(cloth)

}