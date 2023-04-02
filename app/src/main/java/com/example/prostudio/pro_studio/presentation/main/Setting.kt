package com.example.prostudio.pro_studio.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.example.prostudio.R
import com.example.prostudio.pro_studio.presentation.BottomNavigationItem
import com.example.prostudio.pro_studio.presentation.BottomNavigationMenu
import com.example.prostudio.ui.theme.bgColor

@Composable
fun SettingScreen(navController: NavController) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
    ) {

        val (bottomNav, settingContent) = createRefs()

        Text(
            text = "This is Setting Content",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.varela_round_regular)),
            modifier = Modifier
                .constrainAs(settingContent){
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(bottomNav.top)
                },
            color = bgColor
        )


        BottomNavigationMenu(
            selectedItem = BottomNavigationItem.SETTING,
            navController = navController,
            modifier =  Modifier
                .constrainAs(bottomNav){
                    width = Dimension.fillToConstraints
                    start.linkTo(parent.start, 8.dp)
                    end.linkTo(parent.end, 8.dp)
                    bottom.linkTo(parent.bottom, 25.dp)
                }
        )



    }

}