package com.portosdeveloper.protecadminapp.domain.use_cases.user_work_shop

import com.portosdeveloper.protecadminapp.domain.model.UserWorkShop
import com.portosdeveloper.protecadminapp.domain.repository.PackageRepository
import com.portosdeveloper.protecadminapp.domain.repository.UserWorkShopRepository
import javax.inject.Inject

class UpdateWorkProgressList @Inject constructor(private val repository: UserWorkShopRepository) {

    suspend operator fun invoke(userWorkShop: UserWorkShop, newItem: String) = repository.updateWorkProgressList(userWorkShop, newItem)

}