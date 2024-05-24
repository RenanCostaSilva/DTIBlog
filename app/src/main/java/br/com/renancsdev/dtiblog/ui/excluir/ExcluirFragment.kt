package br.com.renancsdev.dtiblog.ui.excluir

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.renancsdev.dtiblog.adapter.excluir.RecyclerViewDeletarListaPost
import br.com.renancsdev.dtiblog.adapter.listar.RecyclerViewListarPosts
import br.com.renancsdev.dtiblog.api.builder.ApiClient
import br.com.renancsdev.dtiblog.api.model.Postagem
import br.com.renancsdev.dtiblog.databinding.FragmentExcluirBinding
import br.com.renancsdev.dtiblog.extension.esconder
import br.com.renancsdev.dtiblog.extension.mostrar
import br.com.renancsdev.dtiblog.extension.toast
import br.com.renancsdev.dtiblog.model.Posts
import br.com.renancsdev.dtiblog.repository.Resultado
import br.com.renancsdev.dtiblog.ui.criar.CriarViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExcluirFragment : Fragment() {

    private var _binding: FragmentExcluirBinding? = null

    private val binding get() = _binding!!
    private val viewModel by viewModel<ExcluirViewModel>()

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View {
        //val notificationsViewModel = ViewModelProvider(this).get(ExcluirViewModel::class.java)

        _binding = FragmentExcluirBinding.inflate(inflater, container, false)
        val root: View = binding.root

        listarPostagensLiveData()
        //excluirPostagem()
        esconderComponentes()

        return root
    }

    override fun onResume() {
        super.onResume()
        //listarPostagens()
        listarPostagensLiveData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun esconderComponentes(){
        binding.tvListaDeletarNadaEncontrado.esconder()
    }

    /* ViewModel */
    private fun listarPostagensLiveData(){
        viewModel.buscaListaUsuarios().observe( binding.root.context as AppCompatActivity) {
            it?.let { resultado ->
                when (resultado) {
                    is Resultado.Sucesso -> {
                        resultado.dado?.let { postagens ->
                            configurarAdapter(postagens , binding)
                            true
                        } ?: false
                    }
                    is Resultado.Erro -> {
                        mostarMensagemDeErro404()
                        false
                    }
                }
            } ?: false
        }
    }
    /* Fim ViewModel */

    private fun mostarMensagemDeErro404(){
        binding.rvListarDeletarPostagem.esconder()
        binding.tvListaDeletarNadaEncontrado.mostrar()
        binding.tvListaDeletarNadaEncontrado.text = "Nenhuma postagem encontrada !"
    }

    /*private fun excluirPostagem(){
        CoroutineScope(Dispatchers.IO).launch {
            chamadaApiExcluir()
        }
    }*/

    /*private fun chamadaApiExcluir(){
        ApiClient.apiService.getAllPost().apply {
            enqueue(object : Callback<ArrayList<Postagem>> {

                override fun onResponse(call: Call<ArrayList<Postagem>>, response: Response<ArrayList<Postagem>>) {
                    if(response.code() == 200){
                       configurarAdapter(response.body()!! , binding)
                    }
                    else if(response.code() == 404){
                        mostarErroPostagemNaoEncontrada()
                    }
                }

                override fun onFailure(call: Call<ArrayList<Postagem>>, t: Throwable) {
                    mostarErroRequisicaoPostagem()
                    binding.root.context.toast("Failed to get response")
                }

            })
        }
    }*/

    private fun configurarAdapter(listaPost: ArrayList<Postagem> , excluirBinding: FragmentExcluirBinding){
        binding.rvListarDeletarPostagem.apply {
            layoutManager = LinearLayoutManager(binding.root.context)
            adapter       = RecyclerViewDeletarListaPost(listaPost , excluirBinding)
        }
    }

    private fun mostarErroPostagemNaoEncontrada(){
        binding.rvListarDeletarPostagem.esconder()
        binding.tvListaDeletarNadaEncontrado.mostrar()
        binding.tvListaDeletarNadaEncontrado.text = "Nenhuma postagem encontrada !"
    }

    private fun mostarErroRequisicaoPostagem(){
        binding.rvListarDeletarPostagem.esconder()
        binding.tvListaDeletarNadaEncontrado.mostrar()
        binding.tvListaDeletarNadaEncontrado.text = "Nenhuma postagem encontrada !"
    }

}