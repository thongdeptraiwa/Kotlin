package com.example.asm

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.absolutePadding
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
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.asm.model.LoginReq
import com.example.asm.model.LoginRes
import com.example.asm.model.RegisterReq
import com.example.kotlin.ViewModel.ViewModelApp

@Composable
fun DangNhap(navController: NavController,viewModel: ViewModelApp = viewModel()){
        // usestate
        var email by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }
        var showPassword by remember {
            mutableStateOf(false)
        }

        val result by viewModel.messageLogin;
        //val result by viewModel.messageLogin.collectAsState()

        val context = LocalContext.current;

        LaunchedEffect(result) {
            val currentMessage = result // Tạo biến trung gian
            if (currentMessage != null && currentMessage.status) {
                Toast.makeText(context, currentMessage.message, Toast.LENGTH_SHORT).show()
                navController.navigate("Home")
            }else if(currentMessage != null && !currentMessage.status){
                Toast.makeText(context, currentMessage.message, Toast.LENGTH_SHORT).show()
            }
        }

        fun dangNhap(){
            //check null
            if(email.trim().isNullOrEmpty()){
                Toast.makeText(context, "Bạn chưa nhập email", Toast.LENGTH_SHORT).show();
                return;
            }
            if(password.trim().isNullOrEmpty()){
                Toast.makeText(context, "Bạn chưa nhập password", Toast.LENGTH_SHORT).show()
                return;
            }
            //call api
            viewModel.loginViewModel(LoginReq(email,password));
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                //.padding(start = 30.dp, end = 30.dp, top = 40.dp),
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 30.dp, top = 40.dp),
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
                text = "Hello !",
                color = Color.Gray,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 10.dp,start = 30.dp)
            )
            Text(
                text = "WELCOME BACK",
                color = Color.Black,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 30.dp,start = 30.dp)
            )
            Card(
                modifier = Modifier
                    .width(350.dp)
                    .height(440.dp),
                colors =  CardDefaults. cardColors(Color.White),
                elevation =  CardDefaults. cardElevation(10.dp),
            ) {
                Column(verticalArrangement= Arrangement.Center ) {
                    Text(
                        text = "Email",
                        modifier = Modifier
                            .padding(start = 20.dp, top = 20.dp),
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
                            .padding(start = 20.dp, bottom = 20.dp), // Đặt chiều rộng tối đa
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
                    //quen mat khau
                    Text(
                        text = "Forgot Password",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth(),
                    )
                    // Nút
                    Button(
                        onClick = { dangNhap() },
                        modifier = Modifier
                            .padding(top = 20.dp, bottom = 20.dp)
                            .width(285.dp)
                            .height(50.dp)
                            .align(Alignment.CenterHorizontally), // Căn giữa
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xF21D1C1C)
                        ),
                        shape = RoundedCornerShape(5.dp),
                    ) {
                        Text(
                            text = "LOG IN",
                            color = Color.White,
                            fontFamily = FontFamily.Serif,
                            fontSize = 18.sp,
                        )
                    }
                    OutlinedButton(
                        onClick = { navController.navigate("DangKi") },
                        modifier = Modifier
                            .width(285.dp)
                            .height(50.dp)
                            .align(Alignment.CenterHorizontally), // Căn giữa
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xF2FFFFFF)
                        ),
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Text(
                            text = "SIGN UP",
                            color = Color.Black,
                            fontFamily = FontFamily.Serif,
                            fontSize = 18.sp,
                        )
                    }
                }
            }
        }
}