package com.example.asm.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.asm.model.ProductModelRoom

@Database(entities = [ProductModelRoom::class], version = 1, exportSchema = false)
abstract class CartDB : RoomDatabase() {

    abstract fun cartDAO(): CartDAO

    companion object {

        @Volatile
        private var INTANCE: CartDB? = null

        fun getIntance(context: Context): CartDB {
            synchronized(this){
                var intance = INTANCE
                if (intance == null){
                    intance = Room.databaseBuilder(
                        context.applicationContext,
                        CartDB::class.java,
                        "cart_db2"
                    ).build()
                }
                return intance
            }

        }

    }

}