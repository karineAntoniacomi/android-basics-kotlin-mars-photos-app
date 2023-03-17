package com.example.android.marsphotos.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// URL base do serviço da Web
private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"

// Build the Moshi object with Kotlin adapter factory that Retrofit will be using
private val moshi = Moshi.Builder()
    // Permite que as anotaçõe Moshi funcionem corretamente
    .add(KotlinJsonAdapterFactory())
    .build()

// Retrofit object with the Moshi converter
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    // Adiciona o URI base do serviço da Web
    .baseUrl(BASE_URL)
    // Cria o objeto Retrofit
    .build()

// A public interface that exposes the [getPhotos] method
// Define como a Retrofit se comunica com o servidor da Web usando solicitações HTTP
interface MarsApiService {
    // Quando este método é invocado, a Retrofit anexa o endpoint photos ao URL base
    /** Returns a [ List] of [ MarsPhoto] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "photos" endpoint will be requested
     * with the GET HTTP method */
    @GET("photos")
    // suspend: Permite chamar o método em corrotinas
    suspend fun getPhotos(): List<MarsPhoto>
}

/** A public Api object that exposes the lazy-initialized Retrofit service */
// Inicializa serviço Retrofit. Objeto Singleton público pode ser acessado pelo app
object MarsApi {
   // lazy: garante que o objeto seja inicializado no primeiro uso do objeto
    val retrofitService : MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java) }
    /** Toda vez que o app chamar MarsApi.retrofitService, o autor
    da chamada acessará o mesmo objeto Singleton da Retrofit que
    implementa o MarsApiService, criado no primeiro acesso. */
}
