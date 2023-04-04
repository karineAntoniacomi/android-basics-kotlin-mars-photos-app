package com.example.android.marsphotos

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android.marsphotos.network.MarsPhoto
import com.example.android.marsphotos.overview.MarsApiStatus
import com.example.android.marsphotos.overview.PhotoGridAdapter

/** Updates the data shown in the [RecyclerView].  */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<MarsPhoto>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    // Informa o RecyclerView quando uma
    // nova lista estiver disponível
    adapter.submitList(data)
}

/** Uses the Coil library to load an image by URL into an [ImageView] */
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


/** This binding adapter displays the [MarsApiStatus] of the network
 * request in an image view.  When the request is loading, it displays
 * a loading_animation.  If the request has an error, it displays a
 * broken image to reflect the connection error. When the request is
 * finished, it hides the image view. */
@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView,
               status: MarsApiStatus?) {

    // bloco when {} que alternar entre os diferentes status
    when (status) {
        MarsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        // Caso para estado de erro
        MarsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        // Caso para estado de concluído
        MarsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        else -> {}
    }
}

