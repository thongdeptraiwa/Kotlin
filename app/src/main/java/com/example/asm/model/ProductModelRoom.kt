package com.example.asm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Carts")
data class ProductModelRoom(
    @PrimaryKey(autoGenerate = true) var uid: Int = 0,
    @ColumnInfo(name = "_id") var _id: String?,
    @ColumnInfo(name = "nameProduct") var nameProduct: String?,
    @ColumnInfo(name = "price") var price: String?,
    @ColumnInfo(name = "image") var image: String?,
    @ColumnInfo(name = "quantity") var quantity: Int?
)