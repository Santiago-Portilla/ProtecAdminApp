package com.portosdeveloper.protecadminapp.domain.use_cases.user_work_shop

import com.portosdeveloper.protecadminapp.domain.model.UserWorkShop
import com.portosdeveloper.protecadminapp.domain.repository.UserWorkShopRepository
import javax.inject.Inject

class UpdateWorkFinishList @Inject constructor(private val repository: UserWorkShopRepository) {
    suspend operator fun invoke(userWorkShop: UserWorkShop, newList: List<String>) = repository.updateWorkFinishList(userWorkShop, newList)
}