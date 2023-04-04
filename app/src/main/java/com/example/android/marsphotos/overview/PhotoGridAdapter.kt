package com.example.android.marsphotos.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsphotos.databinding.GridViewItemBinding
import com.example.android.marsphotos.network.MarsPhoto

/** This class implements a [ RecyclerView] [ ListAdapter] which uses Data
 * Binding to present [List ] data, including computing diffs between lists. */
class PhotoGridAdapter :
    ListAdapter<MarsPhoto, PhotoGridAdapter.MarsPhotoViewHolder>(DiffCallback) {

    /** The MarsPhotosViewHolder constructor takes the binding variable from
     * the associated GridViewItem, which nicely gives it access to the
     * full [MarsPhoto ] information. */
    class MarsPhotoViewHolder(
        private var binding: GridViewItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(MarsPhoto: MarsPhoto) {
            binding.photo = MarsPhoto
            // executePendingBindings faz a atualização ser imediata
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /** Allows the RecyclerView to determine which items have changed
     * when the [List] of [MarsPhoto] has been updated. */
    companion object DiffCallback : DiffUtil.ItemCallback<MarsPhoto>() {

        // O DiffUtil usa este método para descobrir se o novo
        //  objeto MarsPhoto é igual ao objeto MarsPhoto antigo
        override fun areItemsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        // Este método é chamado pelo DiffUtil ao verificar
        // se dois itens têm os mesmos dados
        override fun areContentsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }
    }

    /** Create new [ RecyclerView] item views (invoked by the layout manager) */
    /* O método onCreateViewHolder() precisa retornar um novo
     MarsPhotoViewHolder, criado inflando a GridViewItemBinding
     e usando o LayoutInflater do contexto ViewGroup pai. */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarsPhotoViewHolder {
        return MarsPhotoViewHolder(
            GridViewItemBinding.inflate(
                LayoutInflater.from(parent.context))
        )
    }

    /** Replaces the contents of a view (invoked by the layout manager) */
    /* Método getItem() recebe objeto MarsPhoto associado à
     posição atual do RecyclerView e transmite essa propriedade
     para o método bind() no MarsPhotoViewHolder.*/
    override fun onBindViewHolder(holder: MarsPhotoViewHolder, position: Int) {
        val marsPhoto = getItem(position)
        holder.bind(marsPhoto)
    }
}
