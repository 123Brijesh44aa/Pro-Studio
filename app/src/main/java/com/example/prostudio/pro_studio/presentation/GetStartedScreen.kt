package com.example.prostudio.pro_studio.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.prostudio.R
import com.example.prostudio.ui.theme.*

@OptIn(ExperimentalTextApi::class)
@Preview(showSystemUi = true)
@Composable
fun GetStartedScreen() {

    ConstraintLayout(

    ) {

        val(image, box, welcomeText, someText, getStartButton, alreadyHave) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.girlthree),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(image){
                    width = Dimension.fillToConstraints
                    height = Dimension.value(400.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
                .background(
                    color = Color.White
                )
                .alpha(0.8f)
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .constrainAs(box) {
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(image.bottom, (-40).dp)
                    bottom.linkTo(parent.bottom)
                }
                .background(
                    color = brightGray,
                    shape = RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp)
                ),
            content = {}
        )


        Text(
            text = "Welcome to\n Pro Studio",
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                brush = Brush.verticalGradient(listOf(turquoiseBlue, electricPurple))
            ),
            modifier = Modifier
                .constrainAs(welcomeText){
                    start.linkTo(box.start)
                    end.linkTo(box.end)
                    top.linkTo(box.top, 24.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                },
            textAlign = TextAlign.Center,
            fontSize = 45.sp,
            fontFamily = FontFamily(Font(R.font.montserrat_black))
        )

        Text(
            text = "Welcome to PhotoPro, the photo editing app that " +
                    "helps you turn your photos into works of art! "
                    + "With PhotoPro, you can apply filters, adjust brightness and contrast," +
                    " crop, add text and stickers, and much more to make your photos look stunning.",
            textAlign = TextAlign.Center,
            color = textColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .constrainAs(someText){
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                    start.linkTo(box.start, 24.dp)
                    end.linkTo(box.end, 24.dp)
                    top.linkTo(welcomeText.bottom, 16.dp)
                },
            fontFamily = FontFamily(Font(R.font.montserrat_medium))
        )

        Button(
            onClick = { },
            modifier = Modifier
                .constrainAs(getStartButton) {
                    width = Dimension.fillToConstraints
                    height = Dimension.value(45.dp)
                    start.linkTo(box.start, 22.dp)
                    end.linkTo(box.end, 22.dp)
                    top.linkTo(someText.bottom, 16.dp)
                }
                .clip(CircleShape)
                .background(
                    brush = Brush.horizontalGradient(listOf(turquoiseBlue, electricPurple))
                ),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Unspecified),
            elevation = null

        ){
            Text(text = "Get Started",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.montserrat_black))
            )
        }

        Text(
            text = "Already have account? SignIn",
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                brush = Brush.verticalGradient(listOf(turquoiseBlue, electricPurple))
            ),
            modifier = Modifier
                .clickable {  }
                .constrainAs(alreadyHave){
                    start.linkTo(box.start)
                    end.linkTo(getStartButton.end)
                    top.linkTo(getStartButton.bottom, 8.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                },
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.montserrat_black))
        )

    }
    

}
