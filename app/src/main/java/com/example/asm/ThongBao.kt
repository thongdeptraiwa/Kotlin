package com.example.asm

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
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
fun ThongBao(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {

        // Phần văn bản
        Column(
            modifier = Modifier
                .padding(bottom = 140.dp)
                .fillMaxSize()
                .align(Alignment.Center), // Căn giữa toàn bộ phần văn bản trong màn hình
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally // Căn giữa nội dung theo trục ngang
        ) {
            Text(
                text = "SUCCESS!",
                color = Color.Black,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 10.sp,
                letterSpacing = 3.sp,
                fontFamily = FontFamily.Serif,
                modifier = Modifier.padding(top = 50.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.img_notification),
                contentDescription = "",
                modifier = Modifier
                    .padding(20.dp)
                    .width(290.dp)
                    .height(260.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                text = "Your order will be delivered soon.\n" +
                        "Thank you for choosing our app!",
                //color = Color.Gray,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                //lineHeight = 35.sp,
                textAlign = TextAlign.Justify,
            )

            // Nút
            Button(
                onClick = {navController.navigate("Home")},
                modifier = Modifier
                    .padding(top = 50.dp)
                    .width(315.dp)
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor  = Color(0xF21D1C1C)
                ),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = "Track your orders",
                    color = Color.White,
                    fontFamily = FontFamily.Serif,
                    fontSize = 18.sp,
                )
            }

            OutlinedButton(
                onClick = {navController.navigate("Home")},
                modifier = Modifier
                    .padding(top = 20.dp)
                    .width(315.dp)
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor  = Color(0xF2FFFFFF)
                ),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = "BACK TO HOME",
                    color = Color.Black,
                    fontFamily = FontFamily.Serif,
                    fontSize = 18.sp,
                )
            }

        }
    }
}