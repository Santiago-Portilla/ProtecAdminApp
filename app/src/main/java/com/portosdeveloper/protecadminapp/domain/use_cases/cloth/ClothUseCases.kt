package com.portosdeveloper.protecadminapp.domain.use_cases.cloth

data class ClothUseCases (
    var createCloth: CreateCloth,
    var createTotalCloth : CreateTotalCloth,
    var getTotalMeasureById : GetTotalMeasureById,
    var updateTotalCloth: UpdateTotalCloth,
    var stockTotalCloth : StockTotalCloth,
    var getClothList: GetClothList,
    var updateCloth: UpdateCloth,
    var addDateTotalCloth: AddDateTotalCloth
    )