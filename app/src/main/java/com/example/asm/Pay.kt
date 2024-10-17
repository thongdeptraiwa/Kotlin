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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.asm.R
import java.util.Locale

@Composable
fun Pay(navController: NavController, tien: Int?,) {
    val thue = 5000;
    val finalPrice = tien!! - thue;

    // doi price
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale("vi", "VN"))

    //giao dien
    Column(modifier = Modifier.padding(20.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // icon back
            Image(
                painter = painterResource(id = R.drawable.icon_back),
                contentDescription = null,
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
                    .clickable { navController.popBackStack() }
            )
            Text(text = "Check out", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = "")
        }
        Row(
            modifier = Modifier
                .padding(top = 19.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Shipping Address", color = Color.Gray, fontSize = 18.sp)
            Image(
                painter = painterResource(id = R.drawable.pen),
                contentDescription = null,
                modifier = Modifier
                    .width(16.dp)
                    .height(20.dp)
            )
        }
        Box(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(5.dp)
                )
                .background(Color.White)
                .shadow(
                    elevation = 3.dp, // Độ cao của bóng
                    spotColor = Color(0xFF909090)
                )
        ) {
            Column {
                Text(
                    text = "Bruno Fernandes",
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(20.dp)
                )
                // Đường kẻ ngang
                Divider(
                    color = Color.Gray, // Màu xám
                    thickness = 0.5.dp,   // Độ dày của đường
                )
                Text(
                    text = "25 rue Robert Latouche, Nice, 06200, Côte D’azur, France",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(20.dp)

                )
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 19.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Payment", color = Color.Gray, fontSize = 18.sp)
            Image(
                painter = painterResource(id = R.drawable.pen),
                contentDescription = null,
                modifier = Modifier
                    .width(16.dp)
                    .height(20.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .clip(
                    RoundedCornerShape(5.dp)
                )
                .background(Color.White)
                .shadow(
                    elevation = 3.dp, // Độ cao của bóng
                    spotColor = Color(0xFF909090)
                )
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(70.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.card),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(5.dp)
                        .width(100.dp)
                        .height(75.dp),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "**** **** **** 3947",
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 19.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Delivery method", color = Color.Gray, fontSize = 18.sp)
            Image(
                painter = painterResource(id = R.drawable.pen),
                contentDescription = null,
                modifier = Modifier
                    .width(16.dp)
                    .height(20.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .clip(
                    RoundedCornerShape(5.dp)
                )
                .background(Color.White)
                .shadow(
                    elevation = 3.dp, // Độ cao của bóng
                    spotColor = Color(0xFF909090)
                )
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(70.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.brand),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .width(90.dp)
                        .height(30.dp)
                )
                Text(
                    text = "Fast (2-3days)",
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }
        }

        // bottom
        Column(
            modifier = Modifier
                .padding(top = 40.dp)
                .clip(
                    RoundedCornerShape(5.dp)
                )
                .background(Color.White)
                .shadow(
                    elevation = 3.dp, // Độ cao của bóng
                    spotColor = Color(0xFF909090)
                )
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(7.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Order:", color = Color.Gray, fontSize = 20.sp)
                // tien tu cart
                Text(
                    text = "$ "+currencyFormat.format(tien),
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(7.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Delivery:", color = Color.Gray, fontSize = 20.sp)
                //thue
                Text(
                    text = "$ "+currencyFormat.format(thue),
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(7.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // gia cuoi
                Text(text = "Total:", color = Color.Gray, fontSize = 20.sp)
                Text(
                    text = "$ "+currencyFormat.format(finalPrice),
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        // btn thong bao
        Button(
            onClick = { navController.navigate("ThongBao") },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .offset(x = 0.dp,y = 60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black // Đặt màu nền của nút thành màu đen
            ),
            shape = RoundedCornerShape(10.dp) // Bo tròn 10dp
        ) {
            Text(text = "SUBMIT ORDER", color = Color.White, fontWeight = FontWeight.Bold)
        }


    }
}