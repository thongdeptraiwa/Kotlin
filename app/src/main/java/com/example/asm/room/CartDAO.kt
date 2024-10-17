package com.example.asm.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.asm.model.ProductModelRoom
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDAO {

    @Insert
    suspend fun add(productModelRoom: ProductModelRoom)

    @Query("SELECT * FROM Carts")
    fun getALl(): Flow<List<ProductModelRoom>>

    @Delete
    suspend fun delete(productModelRoom: ProductModelRoom)

    @Update
    suspend fun update(productModelRoom: ProductModelRoom)


//    @Query("SELECT * FROM Carts")
//    fun getAll(): List<ProductModelRoom>
//
//    @Insert
//    fun insert(vararg productModelRoom: ProductModelRoom)
//
//    @Delete
//    fun delete(productModelRoom: ProductModelRoom)
//
//    @Update
//    fun update(productModelRoom: ProductModelRoom)

}