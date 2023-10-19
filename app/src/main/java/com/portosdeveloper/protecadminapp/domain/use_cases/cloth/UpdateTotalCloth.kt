package com.portosdeveloper.protecadminapp.domain.use_cases.cloth

import com.portosdeveloper.protecadminapp.domain.model.TotalCloth
import com.portosdeveloper.protecadminapp.domain.repository.ClothRepository
import javax.inject.Inject

class UpdateTotalCloth @Inject constructor(private val repository: ClothRepository) {

    suspend operator fun invoke(totalCloth: TotalCloth)= repository.updateTotalCloth(totalCloth)

}