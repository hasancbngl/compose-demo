package com.hasancbngl.workmanagerdemo


import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET

interface FileApi {
    @GET("/photo-1607252650355-f7fd0460ccdb?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1170&q=80")
    suspend fun downloadImage(): Response<ResponseBody>

    companion object {
        val instance by lazy {
            Retrofit.Builder()
                .baseUrl("https://images.unsplash.com")
                .build()
                .create(FileApi::class.java)
        }
    }
}