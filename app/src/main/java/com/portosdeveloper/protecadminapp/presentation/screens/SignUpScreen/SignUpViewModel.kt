package com.portosdeveloper.protecadminapp.presentation.screens.SignUpScreen

import android.content.Context
import android.util.Patterns
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.portosdeveloper.protecadminapp.domain.model.Response
import com.portosdeveloper.protecadminapp.domain.model.User
import com.portosdeveloper.protecadminapp.domain.use_cases.auth.AuthUseCases
import com.portosdeveloper.protecadminapp.domain.use_cases.user.UserUseCases
import com.portosdeveloper.protecadminapp.presentation.screens.SignUpScreen.components.SignUpState
import com.portosdeveloper.protecadminapp.presentation.utils.ComposeFileProvider
import com.portosdeveloper.protecadminapp.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val userUseCases: UserUseCases,
    @ApplicationContext private val context: Context
    ): ViewModel() {

    // STATE FORM
    var state by mutableStateOf(SignUpState())

    //USERNAME
    var isNameValid by mutableStateOf(false)
        private set
    var nameErrMsg by mutableStateOf("Campo Obligatorio")
        private set

    //USERNAME
    var isSurNameValid by mutableStateOf(false)
        private set
    var surNameErrMsg by mutableStateOf("Campo Obligatorio")
        private set

    //EMAIL
    var isEmailValid by mutableStateOf(false)
        private set
    var emailErrMsg by mutableStateOf("")
        private set

    //DAYS LIST
    val daysList = (1..31).map { it.toString() }
    var isDayValid by mutableStateOf(false)
        private set
    var dayErrMsg by mutableStateOf("*")
        private set

    //MONTH LIST
    val monthsList = (Calendar.JANUARY..Calendar.DECEMBER).map { month ->
        val calendar = Calendar.getInstance().apply {
            set(Calendar.MONTH, month)
        }
        SimpleDateFormat("MM", Locale.getDefault()).format(calendar.time).toUpperCase(Locale.getDefault()).toInt().toString()
    }
    var isMonthValid by mutableStateOf(false)
        private set
    var monthErrMsg by mutableStateOf("*")
        private set

    //YEAR LIST
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    val yearList = (currentYear downTo currentYear - 100).map { it.toString() }
    var isYearValid by mutableStateOf(false)
        private set
    var yearErrMsg by mutableStateOf("*")
        private set

    //IDENTIFICATION TYPE
    var isIdentificationTypeValid by mutableStateOf(false)
        private set
    var identificationTypeErrMsg by mutableStateOf("Campo Obligatorio")
        private set

    //IDENTIFICATION TYPE

    val identificationOptions = listOf("CC","CE","NUIP")

    //IDENTIFICATION NUMBER
    var isIdentificationNumberValid by mutableStateOf(false)
        private set
    var identificationNumberErrMsg by mutableStateOf("Campo Obligatorio")
        private set

    //PASSWORD
    var isPasswordValid by mutableStateOf(false)
        private set
    var passwordErrMsg by mutableStateOf("Tienen que ser al menos 6 caracteres")
        private set

    //COMFIRM PASSWORD
    var isConfirmPasswordValid by mutableStateOf(false)
        private set
    var confirmPasswordErrMsg by mutableStateOf("Las contraseñas no coinsiden")
        private set

    //BUTTON
    var isEnabledSingUpButton = false

    var signUpResponse by mutableStateOf<Response<FirebaseUser>?>(null)
        private set

    var updateResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    var saveImageResponse by mutableStateOf<Response<String>?>(null)
        private set

    var user = User()

    var file : File? = null

    val resultingActivityHandler = ResultingActivityHandler()

    fun saveImage() = viewModelScope.launch {
        if( file != null){
            saveImageResponse = Response.Loading
            val result = userUseCases.saveImage(file!!)
            saveImageResponse = result
        }
    }

    fun pickerImage() = viewModelScope.launch {
        val result = resultingActivityHandler.getContent("image/*")
        if(result != null){
            file = ComposeFileProvider.createFileFromUri(context, result)
            state = state.copy(image = result.toString())
        }
    }

    fun takePhoto() = viewModelScope.launch {
        val result = resultingActivityHandler.takePicturePreview()
        if (result != null){
            state = state.copy(image = ComposeFileProvider.getPathFromBitmap(context,result))
            file = File(state.image)
        }

    }

    fun onSignUp(){
        user.name = state.name
        user.surName = state.surName
        user.birthday = state.birthday
        user.identificationType = state.identificationType
        user.identificationNumber = state.identificationNumber
        user.email = state.email
        user.password = state.password

        signUp(user)
    }

    fun signUp(user : User) = viewModelScope.launch {
        signUpResponse = Response.Loading
        val result = authUseCases.signUp(user)
        signUpResponse = result
    }

    fun create() = viewModelScope.launch {
        user.id = authUseCases.getCurrentUser()!!.uid
        userUseCases.create(user)
    }

    fun onUpdate(url: String){
        user.image = url
        update(user)
    }

    fun update(user : User) = viewModelScope.launch {
        updateResponse = Response.Loading
        val result = userUseCases.update(user)
        updateResponse = result
    }

    fun onNameInput(name : String){
        state = state.copy(name = name)
    }
    fun onSurNameInput(surName : String){
        state = state.copy(surName = surName)
    }
    fun onEmailInput(email : String){
        state = state.copy(email = email)
    }
    fun onBirthdayInput(birthday: String){
        state = state.copy(birthday = birthday)
    }
    fun onDayInput(day : String){
        state = state.copy(day = day)
    }
    fun onMonthInput(month : String){
        state = state.copy(month = month)
    }
    fun onYearInput(year : String){
        state = state.copy(year = year)
    }
    fun onIdentificationTypeInput(identificationType : String){
        state = state.copy(identificationType = identificationType)
    }
    fun onIdentificationNumberInput(identificationNumber : String){
        state = state.copy(identificationNumber = identificationNumber)
    }
    fun onPasswordInput(password : String){
        state = state.copy(password = password)
    }
    fun onConfirmPasswordInput(confirmPassword : String){
        state = state.copy(confirmPassword = confirmPassword)
    }

    fun enabledSingUpButton(){
        isEnabledSingUpButton =
            isNameValid &&
            isSurNameValid &&
            isEmailValid &&
            isDayValid &&
            isMonthValid &&
            isYearValid &&
            isIdentificationTypeValid &&
            isIdentificationNumberValid &&
            isPasswordValid &&
            isConfirmPasswordValid
    }

    fun getFormattedDate(): String {
        val formattedDay = state.day.ifEmpty { "--" }
        val formattedMonth = state.month.ifEmpty { "--" }
        val formattedYear = state.year.ifEmpty { "----" }
        return "$formattedDay/$formattedMonth/$formattedYear"
    }

    fun validateName(){
        if(state.name.length >= 4){
            isNameValid = true
            nameErrMsg = ""
        }else{
            isNameValid = false
            nameErrMsg = "Campo Obligatorio"
        }
        enabledSingUpButton()
    }

    fun validateSurName(){
        if(state.surName.length >= 4){
            isSurNameValid = true
            surNameErrMsg = ""
        }else{
            isSurNameValid = false
            surNameErrMsg = "Campo Obligatorio"
        }
        enabledSingUpButton()
    }

    fun validateEmail(){
        if(Patterns.EMAIL_ADDRESS.matcher(state.email).matches()){
            isEmailValid = true
            emailErrMsg = ""
        }else{
            isEmailValid = false
            emailErrMsg = "El email no es valido"
        }
        enabledSingUpButton()
    }

    fun validateDay(){
        if(state.day.isNotEmpty()){
            isDayValid = true
            dayErrMsg = ""
        }else{
            isDayValid = false
            dayErrMsg = "*"
        }
        enabledSingUpButton()
    }

    fun validateMonth(){
        if(state.month.isNotEmpty()){
            isMonthValid = true
            monthErrMsg = ""
        }else{
            isMonthValid = false
            monthErrMsg = "*"
        }
        enabledSingUpButton()
    }

    fun validateYear(){
        if(state.year.isNotEmpty()){
            isYearValid = true
            yearErrMsg = ""
        }else{
            isYearValid = false
            yearErrMsg = "*"
        }
        enabledSingUpButton()
    }

    fun validateIdentificationType(){
        if(state.identificationType.isNotEmpty()){
            isIdentificationTypeValid = true
            identificationTypeErrMsg = ""
        }else{
            isIdentificationTypeValid = false
            identificationTypeErrMsg = "Campo Obligatorio"
        }
        enabledSingUpButton()
    }

    fun validateIdentificationNumber(){
        if(state.identificationNumber.length >= 6){
            isIdentificationNumberValid = true
            identificationNumberErrMsg = ""
        }else{
            isIdentificationNumberValid = false
            identificationNumberErrMsg = "Porfavor llene el campo"
        }
        enabledSingUpButton()
    }

    fun validatePassword(){
        if(state.password.length >= 6){
            isPasswordValid = true
            passwordErrMsg = ""
        }else{
            isPasswordValid = false
            passwordErrMsg = "Tienen que ser al menos 6 caracteres"
        }
        enabledSingUpButton()
    }

    fun validateConfirmPassword(){
        if(state.confirmPassword == state.password){
            isConfirmPasswordValid = true
            confirmPasswordErrMsg = ""
        }else{
            isConfirmPasswordValid = false
            confirmPasswordErrMsg = "Las contraseñas no coinsiden"
        }
        enabledSingUpButton()
    }
}