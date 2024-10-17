package com.example.asm.sevices

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update
import com.example.asm.model.ProductModelRoom
//import kotlinx.coroutines.flow.Flow


//@Database(entities = arrayOf(ProductModelRoom::class), version = 1)
//abstract class CartDB : RoomDatabase() {
//    abstract fun CartDAO(): CartDAO
//}

//@Dao
//interface CartDAO {
//    @Query("SELECT * FROM Carts")
//    fun getAll(): List<ProductModelRoom>
//
////    @Query("SELECT * FROM Carts WHERE uid IN (:userIds)")
////    fun loadAllByIds(userIds: IntArray): List<StudentModel>
//
//    @Insert
//    fun insert(vararg productModelRoom: ProductModelRoom)
//
//    @Delete
//    fun delete(productModelRoom: ProductModelRoom)
//
//    @Update
//    fun update(productModelRoom: ProductModelRoom)
//}