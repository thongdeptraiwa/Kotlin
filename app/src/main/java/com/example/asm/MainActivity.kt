package com.example.asm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.asm.repository.Repository
import com.example.asm.room.CartDB
import com.example.asm.ui.theme.ASMTheme
import com.example.kotlin.ViewModel.ViewModelApp
import donad.vn.kotlin.ASM.Cart
import donad.vn.kotlin.ASM.Pay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ASMTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White,
                ) {
                    Main();
                }
            }
        }
    }
}


    @Preview
    @Composable
    fun Main() {
        val context = LocalContext.current
        val db = CartDB.getIntance(context)
        val repository = Repository(db)
        val myViewModel = ViewModelApp(repository)
        val navController = rememberNavController()

        NavHost(navController, startDestination = "ManHinhChao") {
            composable("ManHinhChao") { ManHinhChao (navController) }
            composable("DangNhap") { DangNhap (navController,viewModel = myViewModel) }
            composable("DangKi") { DangKi (navController,viewModel = myViewModel) }
            composable("ThongBao") { ThongBao (navController) }
            composable("Home") { Home (navController,viewModel = myViewModel) }
            composable("Cart") { Cart (navController,viewModel = myViewModel) }
            //composable("Pay") { Pay (navController) }

            composable(
                "ChiTietSanPham/{idProduct}/{price}",
                arguments = listOf(
                    navArgument("idProduct") { type = NavType.StringType },
                    navArgument("price") { type = NavType.StringType }
                )
            ) { backStackEntry -> ChiTietSanPham(
                    navController,
                    viewModel = myViewModel,
                    idProduct = backStackEntry.arguments?.getString("idProduct"),
                    price = backStackEntry.arguments?.getString("price")
                )
            }

            composable(
                "Pay/{tien}",
                arguments = listOf(
                    navArgument("tien") { type = NavType.IntType },
                )
            ) { backStackEntry -> Pay(
                navController,
                tien = backStackEntry.arguments?.getInt("tien"),
            )
            }


        }
    }

