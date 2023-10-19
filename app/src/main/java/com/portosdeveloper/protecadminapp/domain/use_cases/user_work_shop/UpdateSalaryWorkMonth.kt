package com.portosdeveloper.protecadminapp.domain.use_cases.user_work_shop

import com.portosdeveloper.protecadminapp.data.repository.UserWorkShopReposiotryImpl
import com.portosdeveloper.protecadminapp.domain.model.UserWorkShop
import com.portosdeveloper.protecadminapp.domain.repository.UserWorkShopRepository
import javax.inject.Inject

class UpdateSalaryWorkMonth @Inject constructor(private val repository: UserWorkShopRepository) {

    suspend operator fun invoke(userWorkShop: UserWorkShop, newDate: String) = repository.updateSalaryWorkMonth(userWorkShop, newDate)

}