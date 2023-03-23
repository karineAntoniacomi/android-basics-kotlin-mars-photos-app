package com.example.android.marsphotos

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load

// A anotação instrui a vinculação de dados a executar esse adaptador
// de vinculação quando um item da visualização tiver o atributo imageUrl.
@BindingAdapter("imageUrl")
// Arquivo que contém os adaptadores de vinculação
fun bindImage(imgView: ImageView, imgUrl: String?) {
    // O bloco será executado apenas se o objeto não for nulo
    imgUrl?.let {
        // Converte a string de URL em um objeto Uri
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        // Carrega a imagem do objeto imgUri na imgView
        imgView.load(imgUri){
            // Define a imagem de marcador que será usada durante o carregamento
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}