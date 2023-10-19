package com.portosdeveloper.protecadminapp.presentation.screens.PersonalScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.portosdeveloper.protecadminapp.R
import com.portosdeveloper.protecadminapp.domain.model.UserWorkShop
import com.portosdeveloper.protecadminapp.presentation.navigation.DetailsScreen
import com.portosdeveloper.protecadminapp.presentation.ui.theme.Gray700
import com.portosdeveloper.protecadminapp.presentation.ui.theme.ProtecAdminAppTheme

@Composable
fun PersonCard(
    navController: NavHostController,
    userWorkShop: UserWorkShop
){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 15.dp)
            .clickable {
                navController.navigate(
                    route = DetailsScreen.UserWorkShop.passUserWorkShop(
                        userWorkShop.toJson()
                    )
                )
            }
        ,
        elevation = 4.dp,
        shape = RoundedCornerShape(15.dp),
        backgroundColor = Gray700
    ) {
        Row() {
            if(userWorkShop.image != ""){
                AsyncImage(
                    modifier = Modifier
                        .padding(6.dp)
                        .size(55.dp)
                        .clip(CircleShape),
                    model = userWorkShop.image,
                    contentDescription = "UserImage",
                    contentScale = ContentScale.Crop
                )
            }else{
                Image(
                    modifier = Modifier
                        .padding(6.dp)
                        .size(55.dp)
                        .clip(CircleShape),
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "ImageUser")
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                Text(
                    text = "${userWorkShop.name} ${userWorkShop.surName}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color.White
                )
                Text(
                    text = userWorkShop.identificationNumber,
                    color = Color.White
                )
            }

        }

    }
}
