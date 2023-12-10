package com.example.googleauthcomposelearning.presentation.signup

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.waterfallPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.googleauthcomposelearning.R.drawable
import com.example.googleauthcomposelearning.navigation.Screens
import com.example.googleauthcomposelearning.ui.theme.lightBlue
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navController: NavController
) {
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.signUpState.collectAsState(initial = null)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Sign up",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Enter your credentials",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))
        TextField(value = email, onValueChange = {
            email = it
        }, modifier = Modifier.fillMaxWidth(), colors = TextFieldDefaults.textFieldColors(
            containerColor = lightBlue,
            cursorColor = Color.Black,
            disabledLabelColor = lightBlue,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ), shape = RoundedCornerShape(12.dp), singleLine = true, placeholder = {
            Text(text = "Email")
        }

        )
        Spacer(modifier = Modifier.height(16.dp))


        TextField(value = password, onValueChange = {
            password = it
        }, modifier = Modifier.fillMaxWidth(), colors = TextFieldDefaults.textFieldColors(
            containerColor = lightBlue,
            cursorColor = Color.Black,
            disabledLabelColor = lightBlue,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ), shape = RoundedCornerShape(12.dp), singleLine = true, placeholder = {
            Text(text = "Password")
        }

        )


        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                scope.launch {
                    viewModel.signUp(email, password)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black, contentColor = Color.White
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = "SignUp", color = Color.White, modifier = Modifier.padding(8.dp)
            )

        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            if (state.value?.isLoading == true) {
                CircularProgressIndicator()
            }
        }

        Text(
            modifier = Modifier.padding(16.dp).clickable {
                       navController.navigate(Screens.SignInScreen.route)
            },
            text = "Already have an account? Sign in",
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Text(
            text = "or connect with",
            fontWeight = FontWeight.Medium,
            color = Color.Gray
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = {

            }) {
                androidx.compose.material3.Icon(
                    painter = painterResource(id = drawable.ic_google),
                    contentDescription = "google logo",
                    modifier = Modifier.size(50.dp),
                    tint = Color.Unspecified
                )
            }
            Spacer(modifier = Modifier.width(20.dp))

            IconButton(onClick = { /*TODO*/ }) {
                androidx.compose.material3.Icon(
                    painter = painterResource(id = drawable.ic_facebook),
                    contentDescription = "facebook logo",
                    modifier = Modifier.size(50.dp),
                    tint = Color.Unspecified
                )
            }
            
            LaunchedEffect(key1 = state.value?.isSuccess) {
                scope.launch {
                    if (state.value?.isSuccess?.isNotEmpty() == true) {
                        val success = state.value?.isSuccess
                        Toast.makeText(context, success, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

}
