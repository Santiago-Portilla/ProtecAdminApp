package com.portosdeveloper.protecadminapp.presentation.screens.SignUpScreen.components


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.portosdeveloper.protecadminapp.R
import com.portosdeveloper.protecadminapp.presentation.components.*
import com.portosdeveloper.protecadminapp.presentation.screens.SignUpScreen.SignUpViewModel
import com.portosdeveloper.protecadminapp.presentation.ui.theme.Gray200
import com.portosdeveloper.protecadminapp.presentation.ui.theme.Gray500

@Composable
fun SignUpContent(viewModel: SignUpViewModel = hiltViewModel()){


    val state = viewModel.state
    viewModel.resultingActivityHandler.handle()
    var dialogState = remember{ mutableStateOf(false) }

    DialogCapturePicture(
        status = dialogState,
        takePhoto = { viewModel.takePhoto()},
        pickImage = { viewModel.pickerImage() })


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
        Box(
            modifier = Modifier
                .height(230.dp)
                .background(Gray200)
                .fillMaxWidth()
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally


            ) {
                Spacer(modifier = Modifier.height(30.dp))
                if( state.image != ""){
                    AsyncImage(
                        modifier = Modifier
                            .height(120.dp)
                            .width(120.dp)
                            .clip(CircleShape)
                            .clickable { dialogState.value = true },
                        contentScale = ContentScale.Crop,
                        model = state.image,
                        contentDescription = "Selected Image")
                }else{
                    Image(
                        modifier = Modifier
                            .height(120.dp)
                            .clickable { dialogState.value = true },
                        painter = painterResource(id = R.drawable.add_image),
                        contentDescription = "User")

                }

            }
        }
        Card(
            modifier = Modifier
                .padding(
                    start = 40.dp,
                    end = 40.dp,
                    top = 170.dp,
                    bottom = 80.dp),
            backgroundColor = Gray500
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 40.dp,
                            bottom = 0.dp,
                            start = 0.dp,
                            end = 0.dp),
                    text = "REGISTRO",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(
                    modifier = Modifier.height(10.dp)
                )
                Text(
                    text = "Porfavor ingresa estos datos para continuar",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                DefaultTextField(
                    modifier = Modifier.padding(top = 25.dp),
                    value = state.name,
                    onValueChange = { name -> viewModel.onNameInput(name) },
                    validateField = { viewModel.validateName() },
                    label = "Nombre",
                    icon = Icons.Default.Person,
                    errorMsg = viewModel.nameErrMsg

                )
                DefaultTextField(
                    modifier = Modifier.padding(top = 0.dp),
                    value = state.surName,
                    onValueChange = { surName -> viewModel.onSurNameInput(surName) },
                    validateField = { viewModel.validateSurName() },
                    label = "Apellido",
                    icon = Icons.Default.Person,
                    errorMsg = viewModel.surNameErrMsg

                )
                DefaultTextField(
                    modifier = Modifier.padding(top = 0.dp),
                    value = state.email,
                    onValueChange = { email ->  viewModel.onEmailInput(email) },
                    validateField = { viewModel.validateEmail() },
                    label = "Correo Electronico",
                    keyboartype = KeyboardType.Email,
                    icon = Icons.Default.Email,
                    errorMsg = viewModel.emailErrMsg
                )
                DefaultTextField(
                    modifier = Modifier.padding(top = 0.dp),
                    value = viewModel.getFormattedDate(),
                    onValueChange = { birthday ->  viewModel.onBirthdayInput(birthday) },
                    validateField = {  },
                    label = "Fecha de Nacimiento",
                    readOnly = true,
                    icon = Icons.Default.DateRange
                )
                //CAMBIAR POR UN DATE_MENU
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column() {
                        DefaultDropDownMenu(
                            modifier = Modifier.size(width = 88.dp, height = 70.dp),
                            modifierDropDownMenu = Modifier.fillMaxWidth().height(200.dp),
                            value = state.day,
                            onValueChange = {day -> viewModel.onDayInput(day)},
                            validateField = {viewModel.validateDay()},
                            label = "D",
                            errorMsg = viewModel.dayErrMsg,
                            readOnly = true,
                            listItem = viewModel.daysList,
                            onClick = {day -> viewModel.onDayInput(day)}
                        )

                    }
                    Column() {
                        DefaultDropDownMenu(
                            modifier = Modifier.size(width = 88.dp, height = 70.dp),
                            modifierDropDownMenu = Modifier.fillMaxWidth().height(200.dp),
                            value = state.month,
                            onValueChange = {month -> viewModel.onMonthInput(month)},
                            validateField = {viewModel.validateMonth()},
                            label = "M",
                            errorMsg = viewModel.monthErrMsg,
                            readOnly = true,
                            listItem = viewModel.monthsList,
                            onClick = {month -> viewModel.onMonthInput(month)}

                        )

                    }
                    Column() {
                        DefaultDropDownMenu(
                            modifier = Modifier.size(width = 104.dp, height = 70.dp),
                            modifierDropDownMenu = Modifier.fillMaxWidth().height(200.dp),
                            value = state.year,
                            onValueChange = {year -> viewModel.onYearInput(year)},
                            validateField = {viewModel.validateYear()},
                            label = "A",
                            errorMsg = viewModel.yearErrMsg,
                            readOnly = true,
                            listItem = viewModel.yearList,
                            onClick = {year -> viewModel.onYearInput(year)}
                        )
                    }
                }
                DefaultDropDownMenuIcon(
                    modifier = Modifier.padding(top = 0.dp),
                    value = state.identificationType,
                    onValueChange = {identificationType -> viewModel.onIdentificationTypeInput(identificationType)},
                    validateField = {viewModel.validateIdentificationType()},
                    label = "Tipo de Identificaicion",
                    errorMsg = viewModel.identificationTypeErrMsg,
                    readOnly = true,
                    icon = Icons.Default.AccountBox,
                    listItem = viewModel.identificationOptions,
                    onClick = {identificationType -> viewModel.onIdentificationTypeInput(identificationType)}
                )

                DefaultTextField(
                    modifier = Modifier.padding(top = 0.dp),
                    value = state.identificationNumber,
                    onValueChange = { identificationNumber -> viewModel.onIdentificationNumberInput(identificationNumber) },
                    validateField = { viewModel.validateIdentificationNumber() },
                    label = "Numero de identificacion",
                    keyboartype = KeyboardType.Number,
                    icon = Icons.Default.Email,
                    errorMsg = viewModel.identificationNumberErrMsg
                )
                DefaultTextField(
                    modifier = Modifier.padding(top = 0.dp),
                    value = state.password,
                    onValueChange = { password -> viewModel.onPasswordInput(password) },
                    validateField = { viewModel.validatePassword() },
                    label = "Contraseña",
                    icon = Icons.Default.Email,
                    keyboartype = KeyboardType.Email,
                    hideText = true,
                    errorMsg = viewModel.passwordErrMsg
                )
                DefaultTextField(
                    modifier = Modifier.padding(top = 0.dp),
                    value = state.confirmPassword,
                    onValueChange = { confirmPassword ->
                        viewModel.onConfirmPasswordInput(confirmPassword)
                        },
                    validateField = { viewModel.validateConfirmPassword() },
                    label = "Confirmar Contraseña",
                    icon = Icons.Outlined.Email,
                    hideText = true,
                    errorMsg = viewModel.confirmPasswordErrMsg
                )
                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 15.dp,
                            bottom = 15.dp
                        ),
                    text = "REGISTRARSE",
                    onClick = { viewModel.onSignUp() },
                    enabled = viewModel.isEnabledSingUpButton
                )
            }
        }
    }

}


