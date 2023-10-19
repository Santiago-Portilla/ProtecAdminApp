package com.portosdeveloper.protecadminapp.presentation.screens.PersonalScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.portosdeveloper.protecadminapp.R
import com.portosdeveloper.protecadminapp.domain.model.UserWorkShop
import com.portosdeveloper.protecadminapp.presentation.screens.PersonalScreen.PersonalViewModel
import com.portosdeveloper.protecadminapp.presentation.ui.theme.ProtecAdminAppTheme

@Composable
fun PersonalContent(
    usersWorkShopList: List<UserWorkShop>,
    navController: NavHostController
){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 5.dp, top = 5.dp, bottom = 70.dp)
    ){
        items(
            items = usersWorkShopList
        ){ userWorkShop ->
            PersonCard(
                userWorkShop = userWorkShop,
                navController = navController
            )
        }
    }

}
