package br.com.renancsdev.dtiblog.adapter.listar

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.renancsdev.dtiblog.api.model.Postagem
import br.com.renancsdev.dtiblog.databinding.ItemListarPostsBinding
import br.com.renancsdev.dtiblog.model.Post

class RecyclerViewListarPosts(private var posts: ArrayList<Postagem>) :
    RecyclerView.Adapter<ViewHolderListarPosts>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolderListarPosts(ItemListarPostsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    override fun getItemCount() = posts.size
    override fun onBindViewHolder(holder: ViewHolderListarPosts, position: Int) =
        holder.bind(posts[position])

}