package com.example.asm.model

data class Category(val _id: String, val nameCate: String, val image: String)

data class RegisterReq(val email: String, val name: String,val password: String)
data class RegisterRes(val status: Boolean, val message: String)

data class LoginReq(val email: String, val password: String)
data class LoginRes(val status: Boolean, val message: String)

data class Product(
    val _id: String,
    val nameProduct: String,
    val price: String,
    val image: String,
    val description: String,
    val idCate: String
)

data class ProductCart(
    val _id: String,
    val nameProduct: String,
    val price: String,
    val image: String,
    val quantity: Int,
)
