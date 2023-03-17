package com.example.android.marsphotos.network

import com.squareup.moshi.Json

/** This data class defines a Mars photo which includes an ID, and the image URL.
 * The property names of this data class are used by Moshi to match the names of
 * values in JSON. */
data class MarsPhoto (
    // A anotação @Json permite usar nomes de variáveis na classe
    // de dados diferentes dos nomes de chave da resposta JSON
    val id: String,
    //val img_src: String
    @Json(name = "img_src") val imgSrcUrl: String
)