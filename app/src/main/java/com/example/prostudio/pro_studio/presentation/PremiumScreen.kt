package com.example.prostudio.pro_studio.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
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
fun PremiumScreen() {

    ConstraintLayout(

    ) {

        val(image, box, welcomeText, someText, priceBoxes, getStartButton) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.girltwo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(image) {
                    width = Dimension.fillToConstraints
                    height = Dimension.value(400.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
                .background(
                    brush = Brush.verticalGradient(listOf(turquoiseBlue, electricPurple)),
                )
                .alpha(0.3f)

        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .constrainAs(box) {
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(image.bottom, (-120).dp)
                    bottom.linkTo(parent.bottom)
                }
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp),
                ),
            content = {}
        )


        Text(
            text = "Become a\n Premium User",
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
            text = "By Accessing the premium features of this application you can easily " +
                    "create more attractive and innovative designs and edit your existing ones.",
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

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .constrainAs(priceBoxes){
                    start.linkTo(box.start, 16.dp)
                    end.linkTo(box.end, 16.dp)
                    top.linkTo(someText.bottom, 28.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                }
        ) {
            // 100$ per month
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(turquoiseBlue, electricPurple),
                            start = Offset(0f, 0f),
                            end = Offset.Infinite
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable {  }
                    .graphicsLayer(
                        rotationZ = 45f,
                    )
                    .padding(10.dp)

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                            modifier = Modifier
                            .graphicsLayer(
                            rotationZ = -45f)
                                .padding(horizontal = 10.dp, vertical = 30.dp)
                ) {

                    Text(
                        text = "Monthly",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.montserrat_black)),
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "100$",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Per Month",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.montserrat_black)),
                        fontSize = 17.sp
                    )
                }
            }


            // 1000$ per year
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(turquoiseBlue, electricPurple),
                            start = Offset(0f, 0f),
                            end = Offset.Infinite
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable {  }
                    .graphicsLayer(
                        rotationZ = 45f,
                    )
                    .padding(10.dp)

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .graphicsLayer(
                            rotationZ = -45f)
                        .padding(horizontal = 10.dp, vertical = 30.dp)
                ) {

                    Text(
                        text = "Yearly",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.montserrat_black)),
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "1000$",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Per Year",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.montserrat_black)),
                        fontSize = 17.sp
                    )
                }
            }
        }

        Button(
            onClick = { },
            modifier = Modifier
                .constrainAs(getStartButton) {
                    width = Dimension.fillToConstraints
                    height = Dimension.value(45.dp)
                    start.linkTo(box.start, 22.dp)
                    end.linkTo(box.end, 22.dp)
                    top.linkTo(priceBoxes.bottom, 40.dp)
                }
                .clip(CircleShape)
                .background(
                    brush = Brush.horizontalGradient(listOf(turquoiseBlue, electricPurple))
                ),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Unspecified),
            elevation = null

        ){
            Text(text = "Continue",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.montserrat_black))
            )
        }



    }


}
