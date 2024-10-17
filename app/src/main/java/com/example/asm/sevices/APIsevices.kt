package com.example.kotlin.sevices

import com.example.asm.model.Category
import com.example.asm.model.LoginReq
import com.example.asm.model.LoginRes
import com.example.asm.model.Product
import com.example.asm.model.RegisterReq
import com.example.asm.model.RegisterRes
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

// Interface định nghĩa API
interface ApiService {
    @GET("cate/getAllCate")
    suspend fun getCategory(): Array<Category>

    @POST("user/addUser")
    suspend fun register(@Body registerReq: RegisterReq): RegisterRes

    @POST("user/login")
    suspend fun login(@Body loginReq: LoginReq): LoginRes

    @GET("product/getProductCate")
    suspend fun getProductCate(@Query("idCate") idCate: String): Array<Product>

    @GET("product/getProduct")
    suspend fun getProduct(@Query("id") id: String): Product

}

// Object để khởi tạo Retrofit
object RetrofitInstance {
    private const val BASE_URL = "https://kotlin-asm.vercel.app/"

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
