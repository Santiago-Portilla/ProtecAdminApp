package com.portosdeveloper.protecadminapp.presentation.screens.SearchWithNumber

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.portosdeveloper.protecadminapp.presentation.screens.SearchWithNumber.components.SearchWithNumberState
import com.portosdeveloper.protecadminapp.domain.model.Package
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.use_cases._package.PackageUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchWithNumberViewModel @Inject constructor(
    private val packageUseCases: PackageUseCases
)  : ViewModel() {


    var state by mutableStateOf(SearchWithNumberState())

    var getPackageByIdResponse  by mutableStateOf<Response<Package>?>(null)

    var isPackageIdValid: Boolean by mutableStateOf(false)
    var packageErrMsg : String by mutableStateOf("Ingrese un numero de Paquete valido")

    var isEnabledSearchButton = false

    fun onInputId(id: String){
        state = state.copy(id = id)
    }

    fun getPackageById(id : String) = viewModelScope.launch{
        getPackageByIdResponse = Response.Loading
        packageUseCases.newGetPackageById(id).collect{
            getPackageByIdResponse = it
        }
    }

    fun enabledSearchButton(){
        isEnabledSearchButton = isPackageIdValid
    }

    fun validatePackage(){
        if(state.id.isNotEmpty()){
            isPackageIdValid = true
            packageErrMsg = ""
        }else{
            isPackageIdValid = false
            packageErrMsg = "Ingrese un numero de Paquete valido"
        }
        enabledSearchButton()
    }




}