package com.example.prostudio.pro_studio.presentation.authentication

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.example.prostudio.R
import com.example.prostudio.pro_studio.presentation.Toast
import com.example.prostudio.pro_studio.util.Response
import com.example.prostudio.pro_studio.util.Screens
import com.example.prostudio.ui.theme.*


@Composable
fun LoginScreen(navController: NavController, authenticationViewModel: AuthenticationViewModel) {

    ConstraintLayout(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxSize(),
    ) {
        val(backArrow, loginText1, loginText2, loginText3,instantLogin, loginText4,
            emailField, passwordField, rememberMe, forgotPass,
            loginButton, dontHaveAccount) = createRefs()

        val startGuideline = createGuidelineFromStart(16.dp)
        val topGuideline = createGuidelineFromTop(24.dp)
        val endGuideline = createGuidelineFromEnd(16.dp)
        val endGuidelineSecond = createGuidelineFromEnd(40.dp)

        Icon(
            imageVector = Icons.Filled.ArrowBack,
            tint = Color.Black,
            contentDescription = null,
            modifier = Modifier
                .constrainAs(backArrow) {
                    width = Dimension.wrapContent
                    height = Dimension.wrapContent
                    start.linkTo(startGuideline)
                    top.linkTo(topGuideline, 8.dp)
                }
                .clickable {

                }
                .size(33.dp)


        )

        Text(
            color = Color.DarkGray,
            text = "Login now",
            fontWeight = FontWeight.Bold,
            fontSize = 35.sp,
            modifier = Modifier
                .constrainAs(loginText1){
                    start.linkTo(startGuideline)
                    top.linkTo(backArrow.bottom, 16.dp)
                }

        )

        Text(
            text = "Please Login to continue using our app",
            color = textColor,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            modifier = Modifier
                .constrainAs(loginText2){
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                    start.linkTo(startGuideline)
                    top.linkTo(loginText1.bottom)
                    end.linkTo(endGuideline)
                }
        )

        Text(
            text = "Login instantly",
            color = textColor,
            fontWeight = FontWeight.Medium,
            fontSize = 21.sp,
            modifier = Modifier
                .constrainAs(loginText3){
                    start.linkTo(startGuideline)
                    top.linkTo(loginText2.bottom, 30.dp)
                }
        )

        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .constrainAs(instantLogin) {
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                    start.linkTo(startGuideline)
                    end.linkTo(endGuidelineSecond)
                    top.linkTo(loginText3.bottom, 16.dp)
                }
                .padding(0.dp)  // internal padding
                .padding(horizontal = 0.dp)  // (external padding / margin)

        ) {

            Box(
                modifier = Modifier
                    .background(color = brightGray, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clickable { }
                        .padding(0.dp)  // padding
                        .border(
                            width = 1.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(5.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .size(width = 100.dp, height = 27.dp)

                )
            }
            Spacer(modifier = Modifier.width(15.dp))
            Box(
                modifier = Modifier
                    .background(color = brightGray, shape = CircleShape),
                contentAlignment = Alignment.Center
            ){
            Image(
                painter = painterResource(id = R.drawable.facebook),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(0.dp)  // padding
                    .clickable { }
                    .border(
                        width = 1.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .size(width = 100.dp, height = 27.dp)

            )
        }

        }

        Text(
            text = "OR Login with email",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = textColor,
            modifier = Modifier
                .constrainAs(loginText4){
                    start.linkTo(startGuideline)
                    top.linkTo(instantLogin.bottom, 25.dp)
                }
        )


        var emailState by remember { mutableStateOf("") }

        OutlinedTextField(
            value = emailState,
            onValueChange = {emailState = it},
            label = { Text(text = "Email", color = Color.DarkGray) },
            placeholder = { Text(text = "enter email")},
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            ),
            leadingIcon = { Icon(imageVector = Icons.Filled.Email, contentDescription = null, tint = quickSilver) },
            visualTransformation = VisualTransformation.None,
            colors = TextFieldDefaults.textFieldColors(
                focusedLabelColor = MaterialTheme.colors.primaryVariant,
                cursorColor = MaterialTheme.colors.primary,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = MaterialTheme.colors.primaryVariant
            ),
            modifier = Modifier
                .constrainAs(emailField){
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                    start.linkTo(startGuideline)
                    end.linkTo(endGuideline)
                    top.linkTo(loginText4.bottom, 16.dp)
                }
        )

        var passwordState by remember {
            mutableStateOf("")
        }

        OutlinedTextField(
            value = passwordState,
            onValueChange = {passwordState = it},
            label = { Text(text = "Password", color = Color.DarkGray) },
            placeholder = { Text(text = "password")},
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            leadingIcon = { Icon(imageVector = Icons.Filled.Lock, contentDescription = null, tint = quickSilver) },
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.textFieldColors(
                focusedLabelColor = MaterialTheme.colors.primaryVariant,
                cursorColor = MaterialTheme.colors.primary,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = MaterialTheme.colors.primaryVariant
            ),
            modifier = Modifier
                .constrainAs(passwordField){
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                    start.linkTo(startGuideline)
                    end.linkTo(endGuideline)
                    top.linkTo(emailField.bottom, 16.dp)
                },
        )

        var isChecked by remember {
            mutableStateOf(false)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .constrainAs(rememberMe){
                    width = Dimension.wrapContent
                    height = Dimension.wrapContent
                    start.linkTo(startGuideline)
                    top.linkTo(passwordField.bottom, 16.dp)
                }
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = {
                    isChecked = it ; handleCheckBoxChange(isChecked)
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colors.primary
                )
            ) 
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "Remember me", fontSize = 17.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.primary)
        }

        Text(
            text = "Forgot password?",
            color = MaterialTheme.colors.primaryVariant,
            fontSize = 16.sp,
            fontWeight = FontWeight.Black,
            modifier = Modifier
                .constrainAs(forgotPass) {
                    width = Dimension.wrapContent
                    height = Dimension.wrapContent
                    end.linkTo(endGuideline)
                    top.linkTo(passwordField.bottom, 16.dp)
                }
                .clickable { }
                .padding(10.dp)

        )

        Button(
            onClick = {
                      // handle the logic of login
                      authenticationViewModel.signIn(
                          email = emailState,
                          password = passwordState
                      )
            },
            modifier = Modifier
                .constrainAs(loginButton) {
                    width = Dimension.fillToConstraints
                    height = Dimension.value(45.dp)
                    start.linkTo(startGuideline, 30.dp)
                    end.linkTo(endGuideline, 30.dp)
                    top.linkTo(rememberMe.bottom, 32.dp)
                }
                .background(
                    brush = Brush.horizontalGradient(listOf(turquoiseBlue, electricPurple)),
                    shape = RoundedCornerShape(5.dp)
                ),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Unspecified),
            elevation = null

        ){
            Text(text = "Login", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp, color = Color.White)

            when(val response = authenticationViewModel.signInState.value){
                is Response.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.fillMaxSize()
                    )
                }
                is Response.Success ->{
                    if (response.data){
                        navController.navigate(Screens.FeedScreen.route){
                            popUpTo(Screens.LoginScreen.route){
                                inclusive = true
                            }
                        }
                    }
                    else{
                        Toast(message = "Sign In Failed")
                    }
                }
                is Response.Error ->{
                    Toast(message = response.message)
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .constrainAs(dontHaveAccount){
                    width = Dimension.wrapContent
                    height = Dimension.wrapContent
                    start.linkTo(startGuideline)
                    end.linkTo(endGuideline)
                    top.linkTo(loginButton.bottom, 16.dp)
                }
        ) {
            Text(
                text = "Don't have an Account?",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = "Register now",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .clickable {
                        navController.navigate(route = Screens.SignUpScreen.route){
                            launchSingleTop = true
                        }
                    }
                    .padding(10.dp)

            )
        }
        
    }

}

private fun handleCheckBoxChange(isChecked: Boolean) {
    if (isChecked){
        // perform an operation when box is checked
    }
    else{
        // perform an operation when box is unchecked
    }
}

//@Preview(showSystemUi = true)
//@Composable
//fun LoginPreview() {
//    LoginScreen()
//}







