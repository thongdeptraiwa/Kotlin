package donad.vn.kotlin.ASM

import android.icu.text.NumberFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.Room
import coil.compose.AsyncImage
import com.example.asm.R
import com.example.asm.model.ProductModelRoom
import com.example.kotlin.ViewModel.ViewModelApp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.util.Locale

@Composable
fun Cart(navController: NavController,viewModel: ViewModelApp = viewModel()) {

    var promoCode by remember {
        mutableStateOf("")
    }
    var sum by remember {
        mutableStateOf(0)
    }

    // doi price
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale("vi", "VN"))

    //room
    val listCart by viewModel.carts.collectAsState(initial = emptyList())
    fun tangGiamQuantity(x: Int,product: ProductModelRoom){
        // 1 tăng !1 giam
        if(x==1){
            val quantityNew = product.quantity!! + 1;
            // set lại quantity
            viewModel.update(ProductModelRoom(
                uid = product.uid,
                _id = product._id,
                nameProduct = product.nameProduct,
                price = product.price,
                image = product.image,
                quantity = quantityNew
            ))
        }else if(product.quantity!! > 1){
            val quantityNew = product.quantity!! - 1;
            // set lại quantity
            viewModel.update(ProductModelRoom(
                uid = product.uid,
                _id = product._id,
                nameProduct = product.nameProduct,
                price = product.price,
                image = product.image,
                quantity = quantityNew
            ))
        }
    }

    LaunchedEffect(listCart) {
        listCart.forEach { item ->
            sum += item.price!!.toInt() * item.quantity!!.toInt() ;
        }
    }


    //giao dien
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top= 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // back
            Image(
                painter = painterResource(id = R.drawable.icon_back),
                contentDescription = null,
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
                    .clickable { navController.popBackStack() }
            )
            Text(text = "My cart", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = "")
        }

        LazyColumn() {
            itemsIndexed(listCart) { index, item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 25.dp, bottom = 14.dp)
                ) {
                    Box(modifier = Modifier) {
                        //img
                        AsyncImage(
                            model = item.image,
                            contentDescription = null,
                            modifier = Modifier
                                .width(100.dp)
                                .height(100.dp)
                                .clip(RoundedCornerShape(16.dp))
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp)
                    ) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                //name
                                Text(
                                    text = item.nameProduct.toString(),
                                    modifier = Modifier
                                        .width(200.dp)
                                        .height(50.dp),
                                    color = Color.Gray,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                                //delete
                                Image(
                                    painter = painterResource(id = R.drawable.icon_delete),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .width(24.dp)
                                        .height(24.dp)
                                        .clickable { viewModel.delete(ProductModelRoom(
                                            uid = item.uid,
                                            _id = item._id,
                                            nameProduct = item.nameProduct,
                                            price = item.price,
                                            image = item.image,
                                            quantity = item.quantity
                                        )) }
                                )
                            }
                            //price
                            Text(
                                text = "$ "+currencyFormat.format(item.price?.toInt()),
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            Row(
                                modifier = Modifier.padding(top = 20.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                //btn tang quantity
                                Image(
                                    painter = painterResource(id = R.drawable.icon_tang),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .width(30.dp)
                                        .height(30.dp)
                                        .clickable { tangGiamQuantity(1,item) },
                                    contentScale = ContentScale.Crop
                                )
                                // quantity
                                // nếu quantiy <10 ( 0x )
                                if(item.quantity!! < 10){
                                    Text(
                                        text = "0"+item.quantity.toString(),
                                        modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                                        color = Color.Black,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center
                                    )
                                }else{
                                    Text(
                                        text = item.quantity.toString(),
                                        modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                                        color = Color.Black,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center
                                    )
                                }
                                //btn giam quantity
                                Image(
                                    painter = painterResource(id = R.drawable.icon_giam),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .width(30.dp)
                                        .height(30.dp)
                                        .clickable { tangGiamQuantity(-1,item) },
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }
                }
                //item cuối sẽ ko hiện Đường kẻ ngang
                if(index != listCart.size-1){
                    // Đường kẻ ngang
                    Divider(
                        color = Color.Gray, // Màu xám
                        thickness = 0.5.dp,   // Độ dày của đường
                    )
                }


            }
        }





       // bottom
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column{
                Row(verticalAlignment = Alignment.CenterVertically) {
                    TextField(
                        value = promoCode, // Sử dụng biến trạng thái
                        onValueChange = {
                            promoCode = it
                        }, // Cập nhật giá trị khi người dùng nhập liệu
                        placeholder = { Text(text = "Enter your promo code") }, // Thêm placeholder cho TextField
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
//                        colors = TextFieldDefaults.textFieldColors(
//                            containerColor = Color(0xFFe7e7e7), // Màu nền trắng
//                            textColor = Color.Gray, // Màu chữ xám
//                            focusedIndicatorColor = Color.Transparent, // Ẩn viền khi được chọn
//                            unfocusedIndicatorColor = Color.Transparent // Ẩn viền khi không được chọn
//                        )
                    )
                    Button(
                        onClick = { /*TODO*/ }, modifier = Modifier
                            .width(50.dp)
                            .height(55.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black // Đặt màu nền của nút thành màu đen
                        ),
                        shape = RoundedCornerShape(5.dp) // Bo tròn 10dp
                    ) {
                        Text(text = ">", color= Color.White)
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Total:", color = Color.Gray, fontSize = 20.sp)
                    //sum
                    if(!listCart.isNullOrEmpty()){
                        Text(
                            text = "$ "+currencyFormat.format(sum),
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }else{
                        Text(
                            text = "$ "+currencyFormat.format(0),
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                // thanh toan
                Button(
                    onClick = { navController.navigate("Pay/"+sum) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black // Đặt màu nền của nút thành màu đen
                    ),
                    shape = RoundedCornerShape(10.dp) // Bo tròn 10dp
                ) {
                    Text(text = "Check out", color = Color.White)
                }
            }
        }
    }
}