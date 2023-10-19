package com.portosdeveloper.protecadminapp.domain.use_cases._package

import com.portosdeveloper.protecadminapp.domain.repository.PackageRepository
import javax.inject.Inject

class NewGetPackageById @Inject constructor(private  val repository: PackageRepository) {
    suspend operator fun invoke(id: String) = repository.newGetPackageById(id)
}