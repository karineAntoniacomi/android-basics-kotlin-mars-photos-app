package com.example.android.marsphotos.network

import com.squareup.moshi.Json

data class MarsPhoto (
    // A anotação @Json permite usar nomes de variáveis na classe
    // de dados diferentes dos nomes de chave da resposta JSON
    val id: String,
    //val img_src: String
    @Json(name = "img_src") val imgSrcUrl: String
)