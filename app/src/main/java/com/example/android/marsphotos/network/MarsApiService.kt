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
    // Quando este método é invocado, a Retrofit anexa o endpoint photos ao URL base
    @GET("photos")
    // suspend: Permite chamar o método em corrotinas
    suspend fun getPhotos(): String
}

// Inicializa serviço da Retrofit. Objeto Singleton público
// que pode ser acessado pelo restante do app.
object MarsApi {
   // propriedade de inicialização lenta (garante que o objeto
   // seja inicializado no primeiro uso) de objeto da Retrofit
    val retrofitService : MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java) }
    /* Toda vez que o app chamar MarsApi.retrofitService, o autor
    da chamada acessará o mesmo objeto Singleton da Retrofit que
    implementa o MarsApiService, criado no primeiro acesso. */
}
