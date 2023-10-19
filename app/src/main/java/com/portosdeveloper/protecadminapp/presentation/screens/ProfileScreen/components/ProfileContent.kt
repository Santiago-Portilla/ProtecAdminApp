package com.portosdeveloper.protecadminapp.presentation.screens.ProfileScreen.components

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.portosdeveloper.protecadminapp.R
import com.portosdeveloper.protecadminapp.presentation.MainActivity
import com.portosdeveloper.protecadminapp.presentation.components.DefaultButtonImage
import com.portosdeveloper.protecadminapp.presentation.components.QRCodeGenerator
import com.portosdeveloper.protecadminapp.presentation.screens.ProfileScreen.ProfileViewModel
import com.portosdeveloper.protecadminapp.presentation.ui.theme.Gray500

@Composable
fun ProfileContent(viewModel : ProfileViewModel = hiltViewModel()){

    val activity = LocalContext.current as? Activity

    Box(
        modifier = Modifier
            .padding(bottom = 40.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if(viewModel.userData.image != ""){
                AsyncImage(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape),
                    model = viewModel.userData.image,
                    contentDescription = "User Image",
                    contentScale = ContentScale.Crop
                )
            }else{
                Image(
                    modifier = Modifier.size(120.dp),
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "ImageUser")
            }

        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 40.dp,
                        end = 40.dp,
                        top = 190.dp,
                        bottom = 20.dp
                    ),
                backgroundColor = Gray500
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = "${viewModel.userData.name} ${viewModel.userData.surName}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = viewModel.userData.identificationNumber,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        modifier = Modifier.padding(bottom = 20.dp),
                        text = viewModel.userData.email
                    )

                    QRCodeGenerator(
                        modifier = Modifier.padding(15.dp),
                        text = viewModel.getCurrentUser!!.uid,
                        size = 900
                    )
                }

            }

            DefaultButtonImage(
                modifier = Modifier.padding(top = 60.dp),
                text = "Cerrar Sesion",
                icon = painterResource(id = R.drawable.icon_logout),
                onClick = {
                    viewModel.logOut()
                    activity?.finish()
                    activity?.startActivity(Intent(activity, MainActivity :: class.java))
                })
        }
    }
}
