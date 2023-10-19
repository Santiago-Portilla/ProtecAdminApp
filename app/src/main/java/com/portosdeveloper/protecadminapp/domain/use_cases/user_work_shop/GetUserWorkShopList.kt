package com.portosdeveloper.protecadminapp.domain.use_cases.user_work_shop

import com.portosdeveloper.protecadminapp.domain.repository.UserWorkShopRepository
import javax.inject.Inject

class GetUserWorkShopList @Inject constructor(private val repository: UserWorkShopRepository) {
    operator fun invoke() = repository.getUserWorShopList()
}