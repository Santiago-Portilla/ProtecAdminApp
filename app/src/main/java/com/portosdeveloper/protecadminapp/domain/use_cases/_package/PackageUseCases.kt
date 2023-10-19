package com.portosdeveloper.protecadminapp.domain.use_cases._package

data class PackageUseCases(
    var getIdPackage : GetIdPackage,
    var createPackage: CreatePackage,
    var stockPackage: StockPackage,
    var updatePaidJob: UpdatePaidJob,
    var newGetPackageById : NewGetPackageById

)
