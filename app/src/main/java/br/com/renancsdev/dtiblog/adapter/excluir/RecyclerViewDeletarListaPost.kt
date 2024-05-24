package br.com.renancsdev.dtiblog.adapter.excluir

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.renancsdev.dtiblog.api.model.Postagem
import br.com.renancsdev.dtiblog.databinding.FragmentExcluirBinding
import br.com.renancsdev.dtiblog.databinding.ItemListarPostExcluirBinding

class RecyclerViewDeletarListaPost(private var listaPost: List<Postagem>, private var excluirBinding: FragmentExcluirBinding) :RecyclerView.Adapter<ViewHolderDeletarListaPost>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolderDeletarListaPost(ItemListarPostExcluirBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    override fun getItemCount() = listaPost.size
    override fun onBindViewHolder(holder: ViewHolderDeletarListaPost, position: Int){
        var posi = position + 1
        holder.bind(listaPost[position] , excluirBinding , posi)
    }
}