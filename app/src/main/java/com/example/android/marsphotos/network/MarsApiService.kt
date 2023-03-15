package com.example.android.marsphotos.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

// URL base do serviço da Web
private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"

// builder que criar um objeto da Retrofit
private val retrofit = Retrofit.Builder()
    // Busca uma resposta JSON do serviço da Web e retorna como String
    .addConverterFactory(ScalarsConverterFactory.create())
    // Adiciona o URI base do serviço da Web
    .baseUrl(BASE_URL)
    // Cria o objeto Retrofit
    .build()

// Define como a Retrofit se comunica com o servidor da Web usando solicitações HTTP
interface MarsApiService {

    // Quando este método é invicado, a Retrofit anexa o endpoint photos ao URL base
    @GET("photos")
    fun getPhotos(): String
}
