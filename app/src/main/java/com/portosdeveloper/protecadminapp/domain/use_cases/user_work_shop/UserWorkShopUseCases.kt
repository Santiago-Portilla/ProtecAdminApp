package com.portosdeveloper.protecadminapp.domain.use_cases.user_work_shop

data class UserWorkShopUseCases(
    var getCutUserWorkShop: GetCutUserWorkShop,
    var getUserWorkShopList: GetUserWorkShopList,
    var updateWorkProgressList: UpdateWorkProgressList,
    var updateWorkFinishList: UpdateWorkFinishList,
    var updateWorkPaidList: UpdateWorkPaidList,
    var updateSalaryWorkWeek : UpdateSalaryWorkWeek,
    var updateSalaryWorkMonth: UpdateSalaryWorkMonth
)
