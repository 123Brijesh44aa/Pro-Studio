package com.example.prostudio.pro_studio.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.prostudio.pro_studio.util.Screens
import com.example.prostudio.ui.theme.*


enum class BottomNavigationItem(val icon: ImageVector, val label: String, val route: Screens){

    FEED(Icons.Default.Home, "editor", Screens.FeedScreen),
    SETTING(Icons.Default.Settings, "setting", Screens.SettingScreen),
    PROFILE(Icons.Default.Person, "profile", Screens.ProfileScreen)
}


@Composable
fun  BottomNavigationMenu(
    selectedItem : BottomNavigationItem, navController: NavController, modifier: Modifier
){

    Row(
        modifier = modifier
            .background(paleLavender, shape = RoundedCornerShape(100.dp))
            .padding(5.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.Bottom,
    ) {
        for (item in BottomNavigationItem.values()){
            Column(
            ) {
                Image(
                    imageVector = item.icon,
                    contentDescription = "ImageItem",
                    modifier = modifier
                        .size(35.dp)
                        .clickable {
                            navController.navigate(item.route.route) {
                                popUpTo(item.route.route) {
                                    inclusive = false
                                }
                            }
                        },
                    colorFilter = if (item == selectedItem) ColorFilter.tint(frenchViolet) else ColorFilter.tint(Color.DarkGray)
                )
                Text(
                    text = item.label,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (item == selectedItem) Color.White else Color.DarkGray,
                    modifier = Modifier.padding(bottom = 5.dp)
                )
            }

        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun BottomNavBarPreview(){
    BottomNavigationMenu(selectedItem = BottomNavigationItem.FEED, navController = rememberNavController(), modifier = Modifier)
}