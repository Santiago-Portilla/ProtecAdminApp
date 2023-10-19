package com.portosdeveloper.protecadminapp.domain.use_cases._package

import com.portosdeveloper.protecadminapp.domain.repository.PackageRepository
import com.portosdeveloper.protecadminapp.domain.model.Package
import com.portosdeveloper.protecadminapp.domain.repository.UserWorkShopRepository
import javax.inject.Inject

class UpdatePaidJob @Inject constructor(private val repository: PackageRepository) {
    suspend operator fun invoke(packageShirt: Package, job: String, paid: String) = repository.updatePaidJob(packageShirt, job, paid)
}