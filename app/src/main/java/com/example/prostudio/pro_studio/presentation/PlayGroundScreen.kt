package com.example.prostudio.pro_studio.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Undo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.prostudio.pro_studio.presentation.camera.CameraViewModel
import com.example.prostudio.pro_studio.util.Screens
import com.example.prostudio.ui.theme.jet
import com.example.prostudio.ui.theme.textColor

@Composable
fun PlayGroundScreen(navController: NavController, cameraViewModel: CameraViewModel) {

//    val context = LocalContext.current



    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        val(topbar, image) = createRefs()
        val verticalBottomGuideline = createGuidelineFromBottom(80.dp)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .background(jet)
                .padding(12.dp)
                .constrainAs(topbar) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                }
        ) {


            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        navController.navigate(Screens.FeedScreen.route) {
                            popUpTo(Screens.FeedScreen.route) {

                            }
                        }
                    },
                tint = textColor
            )

            Text(
                text = "Reset",
                fontWeight = FontWeight.Bold,
                fontSize = 23.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {},
                color = textColor
            )

            Icon(
                imageVector = Icons.Default.Undo,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { },
                tint = textColor
            )

        }

        val uri = cameraViewModel.imageUri.collectAsState().value

        Log.e("PLAYGROUND_SCREEN","Uri is : $uri")

            Image(
                painter = rememberImagePainter(data = cameraViewModel.imageUri.collectAsState().value),
                contentDescription = "captured image",
                modifier = Modifier
                    .constrainAs(image){
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(topbar.bottom)
                        bottom.linkTo(verticalBottomGuideline)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    }
            )


    }

}