package com.example.asm

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ManHinhChao(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.man_hinh_chao),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Phần văn bản
        Column(
            modifier = Modifier
                .padding(bottom = 140.dp)
                .fillMaxSize()
                .align(Alignment.Center), // Căn giữa toàn bộ phần văn bản trong màn hình
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally // Căn giữa nội dung theo trục ngang
        ) {

            Column(Modifier.padding(25.dp)) {
                Text(
                    text = "MAKE YOUR",
                    color = Color.Gray,
                    fontSize = 24.sp,
                    lineHeight = 10.sp,
                    letterSpacing = 4.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = "HOME BEAUTIFUL",
                    color = Color.Black,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 10.sp,
                    letterSpacing = 3.sp,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(29.dp)
                ) {
                    Text(
                        text = "The best simple place where you",
                        color = Color.Gray,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 35.sp,
                        textAlign = TextAlign.Justify,
                        letterSpacing = 1.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    )
                    Text(
                        text = "discover most wonderful furnitures",
                        color = Color.Gray,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 35.sp,
                        textAlign = TextAlign.Justify,
                        letterSpacing = 0.4.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    )
                    Text(
                        text = "and make your home beautiful",
                        color = Color.Gray,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 35.sp,
                        textAlign = TextAlign.Justify,
                        letterSpacing = 0.2.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    )
                }
            }

        }

        // Nút
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter) // Căn nút ở phía dưới màn hình
                .padding(bottom = 150.dp), // Tùy chỉnh khoảng cách từ nút đến đáy màn hình
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {navController.navigate("DangNhap")},
                modifier = Modifier
                    .width(159.dp)
                    .height(54.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor  = Color(0xF21D1C1C)
                ),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = "Get Started",
                    color = Color.White,
                    fontFamily = FontFamily.Serif,
                    fontSize = 18.sp,
                )
            }
        }
    }
}