package com.portosdeveloper.protecadminapp.presentation.screens.PersonalScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.model.UserWorkShop
import com.portosdeveloper.protecadminapp.domain.use_cases.user_work_shop.UserWorkShopUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonalViewModel @Inject constructor(
    private val userWorkShopUseCases: UserWorkShopUseCases
) : ViewModel() {

    var userGetWorkShopList by mutableStateOf<Response<List<UserWorkShop>>?>(null)

    init{
        getWorkShopList()
    }

    private fun getWorkShopList() = viewModelScope.launch {
        userGetWorkShopList = Response.Loading
        userWorkShopUseCases.getUserWorkShopList().collect(){
            userGetWorkShopList = it
        }
    }


}