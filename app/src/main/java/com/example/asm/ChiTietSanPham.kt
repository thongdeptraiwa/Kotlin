package com.example.asm

import android.icu.text.NumberFormat
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.room.Room
import coil.compose.AsyncImage
import com.example.asm.model.ProductModelRoom
import com.example.kotlin.ViewModel.ViewModelApp
import java.util.Locale

@Composable
fun ChiTietSanPham(
    navController: NavController,
    idProduct: String?,
    price: String? ,
    viewModel: ViewModelApp = viewModel()
) {

        var ko by remember {
            mutableStateOf("0")
        }
        var quantity by remember {
            mutableStateOf(1)
        }

        fun tangGiamQuantity(x: Int){
            if(x==1){
                quantity++;
            }else if(quantity>1){
                quantity--;
            }

            if(quantity>9){
                ko="";
            }else{
                ko="0";
            }
        }

        val context = LocalContext.current;
//        Toast.makeText(context, idProduct, Toast.LENGTH_SHORT).show()
        val product by viewModel.product;

        // doi price
        val currencyFormat = NumberFormat.getCurrencyInstance(Locale("vi","VN"))

        LaunchedEffect(Unit) {
            viewModel.getProductViewModel(idProduct.toString());
        }


        val listCart by viewModel.carts.collectAsState(initial = emptyList())

        fun addProduct(product: ProductModelRoom){
            listCart.forEach { item->
                if(product._id == item._id){
                    // đã có trong cart thì tăng quantity
                    val quantityNew = item.quantity!! + quantity;
                    viewModel.update(ProductModelRoom(
                        uid = item.uid,
                        _id = item._id,
                        nameProduct = item.nameProduct,
                        price = item.price,
                        image = item.image,
                        quantity =quantityNew
                    ))
                    return;
                }
            }
            // chưa có trong cart thì add vào
            viewModel.add(ProductModelRoom(
                _id = product?._id,
                nameProduct = product?.nameProduct,
                price=price,
                image=product?.image,
                quantity = quantity
            ))

        }


        // giao dien
        Column(
            modifier = Modifier
                .fillMaxSize(),
            //verticalArrangement = Arrangement.Center,
            //horizontalAlignment = Alignment.CenterHorizontally // Căn giữa nội dung theo trục ngang
        ) {
            Box{
                //img product
                AsyncImage(
                    model = product?.image,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(440.dp)
                )
                // Nút back
                Button(
                    onClick = {navController.popBackStack()},
                    modifier = Modifier
                        .absoluteOffset(x= 5.dp, y = 40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor  = Color.White,
                    ),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_back),
                        contentDescription = null,
                        modifier = Modifier
                            .width(8.dp)
                            .height(20.dp),
                        contentScale = ContentScale.Crop,
                    )
                }
            }
            /// Name product
            Text(
                text = product?.nameProduct.toString(),
                modifier = Modifier
                    .padding(start = 20.dp, top = 10.dp)
                    .fillMaxWidth(),
                color = Color.Black,
                fontSize = 24.sp,
            )
            // price and quatity
            Row(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ){
                //price
                Text(
                    text = "$ "+currencyFormat.format(price?.toInt()),
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .width(220.dp),
                    color = Color.Black,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                )
                //btn tang quantity
                Image(
                    painter = painterResource(id = R.drawable.icon_tang),
                    contentDescription = null,
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                        .clickable { tangGiamQuantity(1) },
                    contentScale = ContentScale.Crop
                )
                //quatity
                Text(
                    text = "${ko}${quantity.toString()}",
                    modifier = Modifier
                        .width(50.dp),
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                //btn giam quantity
                Image(
                    painter = painterResource(id = R.drawable.icon_giam),
                    contentDescription = null,
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                        .clickable { tangGiamQuantity(-1) },
                    contentScale = ContentScale.Crop
                )
            }
            //sao
            Row(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ){
                Image(
                    painter = painterResource(id = R.drawable.sao),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .width(20.dp)
                        .height(20.dp),
                    contentScale = ContentScale.Crop,
                )
                Text(
                    text = "4.5",
                    modifier = Modifier
                        .padding(start = 10.dp),
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                //reviews
                Text(
                    text = "(50 reviews)",
                    modifier = Modifier
                        .padding(start = 20.dp),
                    color = Color.Gray,
                    fontSize = 14.sp,
                )
            }

            // mieu ta
            Text(
                text = product?.description.toString(),
                //text = "Minimal Stand is made of by natural wood. The design that is very simple and minimal. This is truly one of the best furnitures in any family for now. With 3 different colors, you can easily select the best match for your home. ",
                modifier = Modifier
                    .padding( start = 20.dp, end = 20.dp, top = 10.dp),
                color = Color.Gray,
                fontSize = 14.sp,
                textAlign = TextAlign.Justify
            )

            // 2 btn save and Add to cart
            Row(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ){
                // btn save
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .width(60.dp)
                        .height(60.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFFf3f3f3)),
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.icon_save),
                            contentDescription = null,
                            modifier = Modifier
                                .width(20.dp)
                                .height(20.dp),
                        )
                    }
                }
                // btn Add to cart
                Button(
                    onClick = {addProduct(ProductModelRoom(
                        _id = product?._id,
                        nameProduct = product?.nameProduct,
                        price=price,
                        image=product?.image,
                        quantity = quantity
                    ))},
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .width(250.dp)
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor  = Color(0xF21D1C1C)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Add to cart",
                        color = Color.White,
                        fontSize = 20.sp,
                    )
                }
            }

        }

}