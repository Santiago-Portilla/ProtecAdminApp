package com.portosdeveloper.protecadminapp.presentation.screens.ProfileScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.portosdeveloper.protecadminapp.domain.model.User
import com.portosdeveloper.protecadminapp.domain.use_cases.auth.AuthUseCases
import com.portosdeveloper.protecadminapp.domain.use_cases.user.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val userUseCases: UserUseCases
    ) : ViewModel() {

    var userData by mutableStateOf(User())
    private set

    val getCurrentUser = authUseCases.getCurrentUser()

    init{
        getUserById()
    }

    private fun getUserById() = viewModelScope.launch{
        userUseCases.getUserById(getCurrentUser!!.uid).collect(){
            userData = it
        }
    }
    fun logOut(){
        authUseCases.logOut()
    }
}