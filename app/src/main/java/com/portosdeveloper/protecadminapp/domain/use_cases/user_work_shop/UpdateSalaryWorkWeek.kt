package com.portosdeveloper.protecadminapp.domain.use_cases.user_work_shop

import com.portosdeveloper.protecadminapp.domain.model.UserWorkShop
import com.portosdeveloper.protecadminapp.domain.repository.UserWorkShopRepository
import javax.inject.Inject

class UpdateSalaryWorkWeek @Inject constructor( private val repository: UserWorkShopRepository) {

    suspend operator fun invoke(userWorkShop: UserWorkShop) = repository.updateSalaryWorkWeek(userWorkShop)

}