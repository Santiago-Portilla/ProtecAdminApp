package com.portosdeveloper.protecadminapp.domain.use_cases._package

import com.portosdeveloper.protecadminapp.domain.repository.PackageRepository
import com.portosdeveloper.protecadminapp.domain.model.Package
import javax.inject.Inject

class CreatePackage @Inject constructor(private val repository: PackageRepository){
    suspend operator fun invoke(packageShirt: Package) = repository.create(packageShirt)
}