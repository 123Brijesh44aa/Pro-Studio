package com.example.prostudio.pro_studio.presentation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.prostudio.pro_studio.presentation.BottomNavigationItem
import com.example.prostudio.pro_studio.presentation.BottomNavigationMenu
import com.example.prostudio.pro_studio.presentation.camera.CameraViewModel
import com.example.prostudio.pro_studio.util.Screens
import com.example.prostudio.ui.theme.*

@Composable
fun FeedsScreen(navController: NavController, cameraViewModel: CameraViewModel) {


    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
        ) {

        val (feedContent, bottomNav, topAppBar) = createRefs()

        TopAppBar(
            backgroundColor = MaterialTheme.colors.primaryVariant,
            modifier = Modifier
                .constrainAs(topAppBar){
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                    height = Dimension.value(70.dp)
                }
//                .clip(shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp))
                .fillMaxWidth()
                .padding(0.dp)
                .height(70.dp),
            elevation = 3.dp
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    text = "Hi, Brijesh",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    modifier = Modifier
                        .padding(0.dp)
                        .padding(start = 16.dp),
                    color = textColor
                )
                Image(
                    painter = painterResource(id = com.example.prostudio.R.drawable.girlthree),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(0.dp)
                        .clip(CircleShape)
                        .size(70.dp)
                        .border(width = 3.dp, shape = CircleShape, color = jet)
                )
            }
        }

        Column(
            modifier = Modifier
                .constrainAs(feedContent){
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(topAppBar.bottom)
                    bottom.linkTo(bottomNav.top)
                },
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(
                text = "Photo",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = Modifier
                    .padding(0.dp)
                    .padding(start = 16.dp, top = 10.dp)
                    .align(Alignment.Start),
                color = Color.DarkGray
            )

            Row(
                modifier = Modifier
                    .padding(18.dp)
                    .background(color = purple, shape = RoundedCornerShape(6.dp))
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ){

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clickable {
                            // handle CAMERA
                                navController.navigate(Screens.ShowCameraScreen.route){
                                    popUpTo(Screens.ShowCameraScreen.route){
                                        inclusive = false
                                    }
                                }
                        }
                        .background(color = heliotrope, shape = RoundedCornerShape(8.dp))
                        .padding(20.dp),
                    content = {
                        Image(
                            painter = painterResource(id = com.example.prostudio.R.drawable.icons8_camera_96),
                            contentDescription = "camera image",
                            modifier = Modifier
                                .size(50.dp)
                        )
                    }
                )

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clickable { }
                        .background(color = heliotrope, shape = RoundedCornerShape(8.dp))
                        .padding(20.dp),
                    content = {
                        Image(
                            painter = painterResource(id = com.example.prostudio.R.drawable.icons8_image_96),
                            contentDescription = "gallery image",
                            modifier = Modifier
                                .size(50.dp)
                        )
                    }
                )

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clickable { }
                        .background(color = heliotrope, shape = RoundedCornerShape(8.dp))
                        .padding(0.dp),
                    content = {
                        Image(
                            painter = painterResource(id = com.example.prostudio.R.drawable.girlone),
                            contentDescription = "all image",
                            modifier = Modifier
                                .size(90.dp)
                                .clip(shape = RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                        
                        Text(
                            text = "all",
                            color = Color.LightGray,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                )
            }

            Text(
                text = "Collection",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = Modifier
                    .padding(0.dp)
                    .padding(start = 16.dp)
                    .align(Alignment.Start),
                color = Color.DarkGray
            )

        }

        BottomNavigationMenu(
            selectedItem = BottomNavigationItem.FEED,
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
