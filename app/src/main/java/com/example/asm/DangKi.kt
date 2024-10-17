package com.example.asm

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.asm.model.RegisterReq
import com.example.kotlin.ViewModel.ViewModelApp

@Composable
fun DangKi(navController: NavController,viewModel: ViewModelApp){
    // usestate
    var name by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var showPassword by remember {
        mutableStateOf(false)
    }
    var confirmPassword by remember {
        mutableStateOf("")
    }
    var showConfirmPassword by remember {
        mutableStateOf(false)
    }

    val result by viewModel.messageRegister;
    val context = LocalContext.current;

    LaunchedEffect(result) {
        val currentMessage = result // Tạo biến trung gian
        if (currentMessage != null && currentMessage.status) {
            Toast.makeText(context, currentMessage.message, Toast.LENGTH_SHORT).show()
            navController.navigate("DangNhap")
        }else if(currentMessage != null && !currentMessage.status){
            Toast.makeText(context, currentMessage.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun dangKi(){
        //check null
        if(name.trim().isNullOrEmpty()){
            Toast.makeText(context, "Bạn chưa nhập name", Toast.LENGTH_SHORT).show()
            return;
        }
        if(email.trim().isNullOrEmpty()){
            Toast.makeText(context, "Bạn chưa nhập email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.trim().isNullOrEmpty()){
            Toast.makeText(context, "Bạn chưa nhập password", Toast.LENGTH_SHORT).show()
            return;
        }
        if(confirmPassword.trim().isNullOrEmpty()){
            Toast.makeText(context, "Bạn chưa nhập confirmPassword", Toast.LENGTH_SHORT).show()
            return;
        }
        // check confirmPassword
        if(!confirmPassword.trim().equals(password.trim())){
            Toast.makeText(context, "password không giống confirmPassword", Toast.LENGTH_SHORT).show()
            return;
        }
        //call api
        viewModel.registerViewModel(RegisterReq(email,name,password));
    }


    Column(
        modifier = Modifier
            .fillMaxSize(),
            //.padding(start = 30.dp, end = 30.dp, top = 40.dp),
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 30.dp,top = 40.dp),
            verticalAlignment = Alignment.CenterVertically, // Căn giữa các hình ảnh theo chiều dọc
        ) {
            Image(
                painter = painterResource(id = R.drawable.line),
                contentDescription = null,
                Modifier
                    .width(130.dp)
                    .height(30.dp)
            )
            Spacer(modifier = Modifier.width(10.dp)) // Tạo khoảng cách giữa các hình ảnh
            Image(
                painter = painterResource(id = R.drawable.group),
                contentDescription = null,
                Modifier
                    .width(60.dp)
                    .height(60.dp)
            )
            Spacer(modifier = Modifier.width(10.dp)) // Tạo khoảng cách giữa các hình ảnh
            Image(
                painter = painterResource(id = R.drawable.line),
                contentDescription = null,
                Modifier
                    .width(130.dp)
                    .height(30.dp)
            )
        }

        Text(
            text = "WELCOME",
            color = Color.Black,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 30.dp, start = 20.dp)
        )
        Card(
            modifier = Modifier
                .width(350.dp)
                .height(570.dp),
            colors =  CardDefaults. cardColors(Color.White),
            elevation =  CardDefaults. cardElevation(10.dp),
        ) {
            Text(
                text = "Name",
                modifier = Modifier
                    .padding(start = 20.dp, top = 20.dp),
            )
            TextField(
                value = name, onValueChange = {name = it },
                colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White),
                modifier = Modifier
                    .width(350.dp)
                    .padding(start = 20.dp, bottom = 20.dp) // Đặt chiều rộng tối đa
            )

            Text(
                text = "Email",
                modifier = Modifier
                    .padding(start = 20.dp),
            )
            TextField(
                value = email, onValueChange = {email = it },
                colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White),
                modifier = Modifier
                    .width(350.dp)
                    .padding(start = 20.dp, bottom = 20.dp) // Đặt chiều rộng tối đa
            )

            Text(
                text = "Password",
                modifier = Modifier
                    .padding(start = 20.dp),
            )
            TextField(
                value = password, onValueChange = {password = it },
                colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White),
                modifier = Modifier
                    .width(350.dp)
                    .padding(start = 20.dp, bottom = 20.dp),
                visualTransformation = if (showPassword) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
            )
            Image(
                painter = painterResource(id = R.drawable.eyes),
                contentDescription = null,
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
                    .offset(x = 300.dp,y = -50.dp)
                    .clickable { showPassword = !showPassword},
                contentScale = ContentScale.Crop,
            )

            Text(
                text = "Confirm Password",
                modifier = Modifier
                    .padding(start = 20.dp),
            )
            TextField(
                value = confirmPassword, onValueChange = {confirmPassword = it },
                colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White),
                modifier = Modifier
                    .width(350.dp)
                    .padding(start = 20.dp, bottom = 20.dp),
                visualTransformation = if (showConfirmPassword) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
            )
            Image(
                painter = painterResource(id = R.drawable.eyes),
                contentDescription = null,
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
                    .offset(x = 300.dp,y = -50.dp)
                    .clickable { showConfirmPassword = !showConfirmPassword},
                contentScale = ContentScale.Crop,
            )

            // Nút
            Button(
                onClick = {dangKi() },
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .width(285.dp)
                    .height(50.dp)
                    .align(Alignment.CenterHorizontally), // Căn giữa
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xF21D1C1C)
                ),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(
                    text = "SIGN UP",
                    color = Color.White,
                    fontFamily = FontFamily.Serif,
                    fontSize = 18.sp,
                )
            }

            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Already have account? ",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 3.dp)
                )
                Text(
                    text = "SIGN IN",
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .clickable {navController.navigate("DangNhap")},
                )
            }
        }
    }
}