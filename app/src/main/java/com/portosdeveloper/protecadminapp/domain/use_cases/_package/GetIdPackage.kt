package com.portosdeveloper.protecadminapp.domain.use_cases._package

import com.portosdeveloper.protecadminapp.domain.repository.PackageRepository
import javax.inject.Inject

class GetIdPackage @Inject constructor(private val repository: PackageRepository) {
    operator fun invoke() = repository.getIdPackage()
}