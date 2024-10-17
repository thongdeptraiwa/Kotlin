package com.example.kotlin.ViewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.asm.model.Category
import com.example.asm.model.LoginReq
import com.example.asm.model.LoginRes
import com.example.asm.model.Product
import com.example.asm.model.ProductCart
import com.example.asm.model.ProductModelRoom
import com.example.asm.model.RegisterReq
import com.example.asm.model.RegisterRes
import com.example.asm.repository.Repository
import com.example.kotlin.sevices.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class ViewModelApp(val repository: Repository) : ViewModel(){

    private val _categorys = mutableStateOf<Array<Category>>(emptyArray())
    val categorys: State<Array<Category>> = _categorys

    private val _messageRegister = mutableStateOf<RegisterRes?>(null)
    val messageRegister: State<RegisterRes?> = _messageRegister

    private val _messageLogin = mutableStateOf<LoginRes?>(null)
    val messageLogin: State<LoginRes?> = _messageLogin

//    private val _messageLogin = MutableStateFlow<LoginRes?>(null)
//    val messageLogin: StateFlow<LoginRes?> = _messageLogin.asStateFlow();

    private val _products = mutableStateOf<Array<Product>>(emptyArray())
    val products: State<Array<Product>> = _products

    private val _product = mutableStateOf<Product?>(null)
    val product: State<Product?> = _product


    fun getCategoryViewModel() {
        viewModelScope.launch {
            try {
                _categorys.value = RetrofitInstance.api.getCategory()
            } catch (e: Exception) {
                // Xử lý lỗi nếu cần
                Log.d("=======",e.toString())
            }
        }
    }

    fun registerViewModel(registerReq: RegisterReq) {
        viewModelScope.launch {
            try {
                _messageRegister.value = RetrofitInstance.api.register(registerReq)
            } catch (e: Exception) {
                // Xử lý lỗi nếu cần
                Log.d("=======",e.toString())
            }
        }
    }

    fun loginViewModel(loginReq: LoginReq) {
        viewModelScope.launch {
            try {
                _messageLogin.value = RetrofitInstance.api.login(loginReq)
//                val x = RetrofitInstance.api.login(loginReq);
//                _messageLogin.update { it?.copy(x.status,x.message) }
            } catch (e: Exception) {
                // Xử lý lỗi nếu cần
                Log.d("=======",e.toString())
            }
        }
    }

    fun getProductCateViewModel(idCate: String) {
        viewModelScope.launch {
            try {
                _products.value = RetrofitInstance.api.getProductCate(idCate)
            } catch (e: Exception) {
                // Xử lý lỗi nếu cần
                Log.d("=======",e.toString())
            }
        }
    }

    fun getProductViewModel(id: String) {
        viewModelScope.launch {
            try {
                _product.value = RetrofitInstance.api.getProduct(id)
            } catch (e: Exception) {
                // Xử lý lỗi nếu cần
                Log.d("=======",e.toString())
            }
        }
    }

    fun addItemToCartViewModel(id: String) {
        viewModelScope.launch {
            try {
//                const index = state.cart.findIndex(e => e._id.toString() == item._id.toString());
//                if (index == -1) {
//                    state.cart.push({ ...item, so_luong: 1 });
//                } else {
//                    state.cart[index].so_luong++;
//                }

                _product.value = RetrofitInstance.api.getProduct(id)
            } catch (e: Exception) {
                // Xử lý lỗi nếu cần
                Log.d("=======",e.toString())
            }
        }
    }

    // cart
    fun add(productModelRoom: ProductModelRoom) {
        viewModelScope.launch {
            repository.addProductToRoom(productModelRoom)
        }
    }

    val carts = repository.getAllStudents()

    fun delete(productModelRoom: ProductModelRoom) {
        viewModelScope.launch {
            repository.deleteProductFromRoom(productModelRoom)
        }
    }

    fun update(productModelRoom: ProductModelRoom) {
        viewModelScope.launch {
            repository.updateProduct(productModelRoom)
        }
    }

}
