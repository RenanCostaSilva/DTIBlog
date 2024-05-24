package br.com.renancsdev.dtiblog.ui.criar

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.renancsdev.dtiblog.api.builder.ApiClient
import br.com.renancsdev.dtiblog.databinding.FragmentCriarBinding
import br.com.renancsdev.dtiblog.extension.limpar
import br.com.renancsdev.dtiblog.extension.toast
import br.com.renancsdev.dtiblog.model.Posts
import br.com.renancsdev.dtiblog.repository.Resultado
import br.com.renancsdev.dtiblog.ui.listar.fragment.ListarViewModel
import br.com.renancsdev.dtiblog.util.DataAtual
import br.com.renancsdev.dtiblog.util.Validacao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CriarFragment : Fragment() {

    private var _binding: FragmentCriarBinding? = null

    private val binding get() = _binding!!
    private val viewModel by viewModel<CriarViewModel>()
    private val postagem: Posts by inject()
    private val validacao: Validacao by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //val criarViewModel = ViewModelProvider(this)[CriarViewModel::class.java]

        _binding = FragmentCriarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnFragAdcionarPost.setOnClickListener {

            if(validacao.validarPostagem(binding.editTextTitulo , binding.editTextPost, binding.root.context)){
                criarPost()
            }
            /*if(!binding.editTextPost.text.isNullOrEmpty() && !binding.editTextTitulo.text.isNullOrEmpty()){
                criarPost()
            }
            else{
                if(binding.editTextTitulo.text.isNullOrBlank() ){
                    binding.root.context.toast("Título para o post em Branco !")
                }
                else if(binding.editTextPost.text.isNullOrBlank()){
                    binding.root.context.toast("Texto para o post em Branco !")
                }
            }*/

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun criarPost(){
        criarPostagensLiveData(criarObjetoDePostagem())
        //criarPostagem(criarObjetoDePostagem())
        binding.root.context.toast("Postagem adicionado com sucesso!")
        limparCampos()
    }

    private fun criarObjetoDePostagem(): Posts {
        postagem.title = binding.editTextTitulo.text.toString()
        postagem.postText = binding.editTextPost.text.toString()
        postagem.dateCreationPost = DataAtual().dataSistema()
       return postagem
    }

    private fun limparCampos(){
        binding.editTextPost.limpar()
        binding.editTextTitulo.limpar()
    }

    /*private fun criarPostagem(postagem: Posts){
        CoroutineScope(Dispatchers.IO).launch {
            chamadaAPi(postagem)
        }
    }*/

    /*private fun chamadaAPi(postagem: Posts){
        ApiClient.apiService.criar(postagem).apply {
            enqueue(object : Callback<Posts> {
                override fun onResponse(call: Call<Posts>, response: Response<Posts>) {}
                override fun onFailure(call: Call<Posts>, t: Throwable) {
                    Log.e("Repository", "${t.message}")
                    Toast.makeText(binding.root.context , "Failed to get response" , Toast.LENGTH_SHORT).show()
                }
            })
        }
    }*/

    /* ViewModel */
    private fun criarPostagensLiveData(postagem: Posts){
        viewModel.criarPostagem(postagem).observe( binding.root.context as AppCompatActivity) {
            it?.let { resultado ->
                when (resultado) {
                    is Resultado.Sucesso -> {
                        resultado.dado?.let {
                            true
                        } ?: false
                    }
                    is Resultado.Erro -> {
                        binding.root.context.toast("${resultado.exception}")
                        false
                    }
                }
            } ?: false
        }
    }
    /* Fim ViewModel */

}