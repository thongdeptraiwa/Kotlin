package com.example.asm.repository

import com.example.asm.model.ProductModelRoom
import com.example.asm.room.CartDB


class Repository(val cartDB: CartDB) {
    suspend fun addProductToRoom(productModelRoom: ProductModelRoom){
        cartDB.cartDAO().add(productModelRoom)
    }

    fun getAllStudents() = cartDB.cartDAO().getALl()

    suspend fun deleteProductFromRoom(productModelRoom: ProductModelRoom) {
        cartDB.cartDAO().delete(productModelRoom)
    }

    suspend fun updateProduct(productModelRoom: ProductModelRoom){
        cartDB.cartDAO().update(productModelRoom)
    }
}