package com.portosdeveloper.protecadminapp.domain.use_cases.cloth

import com.portosdeveloper.protecadminapp.domain.model.TotalCloth
import com.portosdeveloper.protecadminapp.domain.repository.ClothRepository
import javax.inject.Inject

class AddDateTotalCloth @Inject constructor(private val repository: ClothRepository) {
    suspend operator fun invoke(newDate: String, totalCloth: TotalCloth) = repository.addDateCloth(newDate,totalCloth)
}