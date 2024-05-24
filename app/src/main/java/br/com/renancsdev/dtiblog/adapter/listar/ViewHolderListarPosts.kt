package br.com.renancsdev.dtiblog.adapter.listar

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.renancsdev.dtiblog.R
import br.com.renancsdev.dtiblog.api.model.Postagem
import br.com.renancsdev.dtiblog.databinding.FragmentListarBinding
import br.com.renancsdev.dtiblog.databinding.ItemListarPostsBinding
import br.com.renancsdev.dtiblog.extension.formatePTBR
import br.com.renancsdev.dtiblog.ui.listar.activity.ListarDetalheActivity


class ViewHolderListarPosts(private var binding: ItemListarPostsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Postagem ) {

        binding.tvItemPostTittle.text = post.title
        binding.tvItemPostData.text = post.dateCreationPost.formatePTBR()
        binding.tvItemPostTexto.text = post.postText

        binding.cardItemPost.setOnClickListener {
            binding.root.context.startActivity(Intent(binding.root.context, ListarDetalheActivity::class.java).putExtra("id", post.id))
        }

    }

}