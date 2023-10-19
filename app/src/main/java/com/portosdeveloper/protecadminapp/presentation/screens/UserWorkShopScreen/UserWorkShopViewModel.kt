package com.portosdeveloper.protecadminapp.presentation.screens.UserWorkShopScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.model.UserWorkShop
import com.portosdeveloper.protecadminapp.domain.model.Package
import com.portosdeveloper.protecadminapp.domain.use_cases._package.PackageUseCases
import com.portosdeveloper.protecadminapp.domain.use_cases.user_work_shop.UserWorkShopUseCases
import com.portosdeveloper.protecadminapp.presentation.utils.ActualDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserWorkShopViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val packageUseCases: PackageUseCases,
    private val userWorkShopUseCases: UserWorkShopUseCases
) : ViewModel() {

    val data = savedStateHandle.get<String>("userWorkShop")
    val userWorkShop = UserWorkShop.fromJson(data!!)
    val actualDate = ActualDate()

    var getPackageByIdResponse  by mutableStateOf<Response<Package>?>(null)
    var updatePaidJobResponse  by mutableStateOf<Response<Boolean>?>(null)
    var updateWorkPaidListResponse  by mutableStateOf<Response<Boolean>?>(null)
    var updateWorkFinishListResponse  by mutableStateOf<Response<Boolean>?>(null)
    var updateSalaryWorkWeekResponse  by mutableStateOf<Response<Boolean>?>(null)
    var updateSalaryWorkMonthResponse  by mutableStateOf<Response<Boolean>?>(null)

    fun getPackageById(id : String) = viewModelScope.launch{
        getPackageByIdResponse = Response.Loading
        packageUseCases.newGetPackageById(id).collect{
            getPackageByIdResponse = it
        }
    }

    fun updatePaidJob(packageShirt: Package) = viewModelScope.launch {
        updatePaidJobResponse = Response.Loading
        val result = packageUseCases.updatePaidJob(packageShirt,userWorkShop.job,actualDate.actualDate)
        updatePaidJobResponse = result
    }

    fun updateWorkPaidList(newItem: String) = viewModelScope.launch {
        updateWorkPaidListResponse = Response.Loading
        val result = userWorkShopUseCases.updateWorkPaidList(userWorkShop,newItem)
        updateWorkPaidListResponse = result
    }

    fun newFinishList(userWorkShop: UserWorkShop, shirtPackage: Package) : List<String>{
        return userWorkShop.workListFinish.filterNot { it.contains(shirtPackage.id, ignoreCase = true)  }
    }

    fun updateWorkFinishList(userWorkShop: UserWorkShop, newList:List<String>) = viewModelScope.launch{
        updateWorkFinishListResponse = Response.Loading
        val result = userWorkShopUseCases.updateWorkFinishList(userWorkShop,newList)
        updateWorkFinishListResponse = result
    }

    fun updateSalaryWorkWeek() = viewModelScope.launch {
        updateSalaryWorkWeekResponse = Response.Loading
        val result = userWorkShopUseCases.updateSalaryWorkWeek(userWorkShop)
        updateSalaryWorkWeekResponse = result
    }

    fun updateSalaryWorkMonth() = viewModelScope.launch {
        updateSalaryWorkWeekResponse = Response.Loading
        val result = userWorkShopUseCases.updateSalaryWorkMonth(userWorkShop, actualDate.date)
        updateSalaryWorkWeekResponse = result
    }




}