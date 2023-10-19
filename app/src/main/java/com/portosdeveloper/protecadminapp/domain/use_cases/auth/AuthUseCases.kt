package com.portosdeveloper.protecadminapp.domain.use_cases.auth

data class AuthUseCases(
    val login: Login,
    val getCurrentUser: GetCurrentUser,
    val signUp: SignUp,
    val logOut: LogOut
)
