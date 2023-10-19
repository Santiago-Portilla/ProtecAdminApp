package com.portosdeveloper.protecadminapp.presentation.screens.StockProgress

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.use_cases._package.PackageUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.portosdeveloper.protecadminapp.domain.model.Package
import kotlinx.coroutines.launch

@HiltViewModel
class StockProgressViewModel @Inject constructor(
    private val packageUseCases: PackageUseCases
    ) : ViewModel(){

    var packageShirtResponse by mutableStateOf<Response<List<Package>>?>(null)

    init {
        getPackage()
    }

    private fun getPackage() = viewModelScope.launch {
        packageShirtResponse = Response.Loading
        packageUseCases.stockPackage().collect(){
            packageShirtResponse = it
        }

    }
}