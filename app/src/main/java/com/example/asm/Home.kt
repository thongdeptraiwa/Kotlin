package com.example.asm

import android.icu.text.NumberFormat
import android.provider.ContactsContract.CommonDataKinds.Im
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.room.Room
import coil.compose.AsyncImage
import com.example.asm.model.Category
import com.example.kotlin.ViewModel.ViewModelApp
import java.util.Locale

@Composable
fun Home(navController: NavController, viewModel: ViewModelApp = viewModel()) {
    var idCate by remember {
        mutableStateOf("")
    }

    //cart
    val listCart by viewModel.carts.collectAsState(initial = emptyList())

    //api
    val categorys by viewModel.categorys;
    val products by viewModel.products;

    LaunchedEffect(Unit) {
        viewModel.getCategoryViewModel();

    }

    LaunchedEffect(categorys) {
        if(!categorys.isEmpty()){
            idCate = categorys[0]?._id.toString();
        }
    }

    LaunchedEffect(idCate) {
        if(!idCate.equals("")){
            viewModel.getProductCateViewModel(idCate);
        }
    }

    // doi price
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale("vi", "VN"))

    // giao dien
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.search),
                contentDescription = null,
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
            )
            Column() {
                Text(
                    text = "Make home",
                    fontSize = 18.sp,
                    color = Color(0xFF909090),
                    modifier = Modifier.width(290.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "BEAUTIFUL",
                    fontSize = 18.sp,
                    color = Color.Black,
                    modifier = Modifier.width(290.dp),
                    textAlign = TextAlign.Center
                )
            }
            // gio hang
            Image(
                painter = painterResource(R.drawable.gio_hang),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .width(24.dp)
                    .height(24.dp)
                    .clickable { navController.navigate("Cart") }
            )
            // so luong item trong cart
            if(!listCart.isNullOrEmpty()){
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape) // Cắt hình thành hình tròn
                        .background(Color.Red)
                        .offset(x = 2.dp, y = -3.dp)

                ){
                    Text(
                        text = listCart.size.toString(),
                        color = Color.White,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,

                    )
                }

//                Button(
//                    onClick = { /*TODO*/ },
//                    modifier = Modifier
//                        .width(30.dp)
//                        .height(30.dp)
//                        //.background(Color.Red)
//                        //.offset(x = -0.dp, y = 0.dp)
//                ) {
//
//                }

            }
        }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(top = 20.dp)
        ) {
            items(categorys) {
                item -> Column (
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .clickable { idCate = item._id },
                    verticalArrangement = Arrangement.Center,
                ){
                    Card(
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .align(Alignment.CenterHorizontally),
                    ) {
                        AsyncImage(
                            model = item.image,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(5.dp)
                                .background(
                                    if (idCate.equals(item._id)) {
                                        Color.Black
                                    } else {
                                        Color.White
                                    }
                                ),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Text(
                        text = item.nameCate, fontSize = 14.sp,
                        color = if (idCate.equals(item._id)) {
                            Color.Black
                        } else {
                            Color.Gray
                        },
                    )
                }
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(products) { item ->
                Card(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        //.size(width = 157.dp, height = 236.dp)
                        .background(color = Color.White)
                        .clickable { navController.navigate("ChiTietSanPham/" + item._id + "/" + item.price) }
                ) {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.White)) {
                        AsyncImage(
                            model = item.image,
                            contentDescription = null,
                            modifier = Modifier
                                .size(width = 160.dp, height = 180.dp),
                            contentScale = ContentScale.Crop
                        )
                        Image(
                            painter = painterResource(id = R.drawable.icon_tui),
                            contentDescription = "",
                            modifier = Modifier
                                .size(width = 30.dp, height = 30.dp)
                                .offset(x = 110.dp, y = -30.dp),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            text = item.nameProduct,
                            modifier = Modifier
                                .width(157.dp)
                                .height(20.dp),
                        )

                        Text(
                            text = "$ "+currencyFormat.format(item.price.toInt()),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

            }
        }


    }
}