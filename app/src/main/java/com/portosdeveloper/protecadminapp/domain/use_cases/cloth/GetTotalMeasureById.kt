package com.portosdeveloper.protecadminapp.domain.use_cases.cloth

import com.portosdeveloper.protecadminapp.domain.repository.ClothRepository
import javax.inject.Inject

class GetTotalMeasureById @Inject constructor(private val repository: ClothRepository) {

    operator fun invoke(id : String) = repository.getTotalMeasureById(id)

}