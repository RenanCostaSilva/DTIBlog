package br.com.renancsdev.dtiblog.adapter.excluir

import android.app.Dialog
import android.util.Log
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.renancsdev.dtiblog.R
import br.com.renancsdev.dtiblog.api.builder.ApiClient
import br.com.renancsdev.dtiblog.api.model.Postagem
import br.com.renancsdev.dtiblog.databinding.FragmentExcluirBinding
import br.com.renancsdev.dtiblog.databinding.ItemListarPostExcluirBinding
import br.com.renancsdev.dtiblog.databinding.LayoutDialogExcluirBinding
import br.com.renancsdev.dtiblog.extension.esconder
import br.com.renancsdev.dtiblog.extension.formatePTBR
import br.com.renancsdev.dtiblog.extension.mostrar
import br.com.renancsdev.dtiblog.extension.toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ViewHolderDeletarListaPost(var binding: ItemListarPostExcluirBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(postagem: Postagem, feb: FragmentExcluirBinding, position: Int){

        mostarDados(postagem)
        eventoBotoes(postagem , feb , position)

    }

    private fun mostarDados(postagem: Postagem){
        binding.tvItemDeletePostTittle.text = postagem.title
        binding.tvItemDeletePostData.text = postagem.dateCreationPost.formatePTBR()
        binding.tvItemDeletePostTexto.text = postagem.postText
    }

    private fun eventoBotoes(postagem: Postagem, feb: FragmentExcluirBinding, position: Int){
        binding.llDeletarPost.setOnClickListener {
            excluirDialog(postagem.id , binding.root.context as AppCompatActivity , feb , position)
        }
    }

    private fun excluirDialog(id: Long , activity: AppCompatActivity , feb: FragmentExcluirBinding , position: Int) {
        val dialog = Dialog(binding.root.context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)

        val dialogBinding = LayoutDialogExcluirBinding.inflate(activity.layoutInflater)
        dialog.setContentView(dialogBinding.root)

        dialogBinding.tvMsgDelete.text = activity.getString(R.string.textview_dialog_msg_excluir)
        dialogBinding.btnMsgDeleteCancel.text = activity.getString(R.string.btn_dialog_msg_excluir_cancel)
        dialogBinding.btnMsgDeleteOk.text = activity.getString(R.string.btn_dialog_msg_excluir_ok)

        dialogBinding.btnMsgDeleteCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialogBinding.btnMsgDeleteOk.setOnClickListener {
            excluirPost(id , feb , position)
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun mostrarErroPostagemNaoEncontrada(feb: FragmentExcluirBinding){
        feb.rvListarDeletarPostagem.esconder()
        feb.tvListaDeletarNadaEncontrado.mostrar()
        feb.tvListaDeletarNadaEncontrado.text = binding.root.context.resources.getString(R.string.tv_titulo_criar_post)
    }

    private fun excluirPost(id: Long , feb: FragmentExcluirBinding , position: Int){
        excluirPostagem(id,feb,position)
        feb.rvListarDeletarPostagem.adapter?.notifyItemRemoved(position)
        binding.llDeletarPost.esconder()
        feb.rvListarDeletarPostagem.adapter?.notifyDataSetChanged()
    }

    private fun excluirPostagem(id: Long , feb: FragmentExcluirBinding , position: Int){
        CoroutineScope(Dispatchers.IO).launch {
            chamadaAPiDeletar(id , feb)
        }
    }

    private  fun chamadaAPiDeletar(id: Long, feb: FragmentExcluirBinding){
        ApiClient.apiService.deletarPostPorID(id).apply {
            enqueue(object : Callback<ResponseBody> {

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if(response.code() == 200 || response.code() == 201 || response.code() == 202 || response.code() == 203){
                        binding.root.context.toast("Postagem deletada com sucesso")
                    }
                    else if(response.code() == 404){
                        mostrarErroPostagemNaoEncontrada(feb)
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("Repository", "${t.message}")
                    binding.root.context.toast("Failed to get response")
                }

            })
        }
    }

}