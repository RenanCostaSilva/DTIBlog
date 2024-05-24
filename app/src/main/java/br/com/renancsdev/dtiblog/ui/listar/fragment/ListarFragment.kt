package br.com.renancsdev.dtiblog.ui.listar.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.renancsdev.dtiblog.MainActivity
import br.com.renancsdev.dtiblog.R
import br.com.renancsdev.dtiblog.adapter.listar.RecyclerViewListarPosts
import br.com.renancsdev.dtiblog.api.builder.ApiClient
import br.com.renancsdev.dtiblog.api.model.Postagem
import br.com.renancsdev.dtiblog.databinding.FragmentListarBinding
import br.com.renancsdev.dtiblog.extension.esconder
import br.com.renancsdev.dtiblog.extension.mostrar
import br.com.renancsdev.dtiblog.extension.toast
import br.com.renancsdev.dtiblog.repository.Resultado
import br.com.renancsdev.dtiblog.ui.criar.CriarFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListarFragment : Fragment() {

    private var _binding: FragmentListarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel by viewModel<ListarViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //val homeViewModel = ViewModelProvider(this)[ListarViewModel::class.java]

        _binding = FragmentListarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        esconderComponentes()
        //listarPostagens()
        listarPostagensLiveData()

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

    /* ViewModel */
    private fun listarPostagensLiveData(){
        viewModel.buscaListaUsuarios().observe( binding.root.context as AppCompatActivity) {
            it?.let { resultado ->
                when (resultado) {
                    is Resultado.Sucesso -> {
                        resultado.dado?.let { postagens ->
                            configurarAdapter(postagens)
                            true
                        } ?: false
                    }
                    is Resultado.Erro -> {
                        mostarMensagemDeErro404()
                        //binding.root.context.toast("${resultado.exception}")
                        false
                    }
                }
            } ?: false
        }
    }
    /* Fim ViewModel */

    private fun esconderComponentes(){
      binding.pbListarPostagem.esconder()
      binding.tvListaNadaEncontrado.esconder()
    }

    /*private fun listarPostagens(){
        binding.pbListarPostagem.mostrar()
        CoroutineScope(IO).launch {
            chamadaAPi()
            withContext(Main){
                binding.pbListarPostagem.esconder()
            }
        }
    }*/

    private fun configurarAdapter(listaPost: ArrayList<Postagem>){
        binding.rvListarPostagem.apply {
            layoutManager = LinearLayoutManager(binding.root.context)
            adapter       = RecyclerViewListarPosts(listaPost)
        }
    }

    /*private  fun chamadaAPi(){
        ApiClient.apiService.getAllPost().apply {
            enqueue(object : Callback<ArrayList<Postagem>> {

                override fun onResponse(call: Call<ArrayList<Postagem>>, response: Response<ArrayList<Postagem>>) {
                    if(response.code() == 200){
                        configurarAdapter(response.body()!!)
                    }else if(response.code() == 404){
                        binding.rvListarPostagem.esconder()
                        binding.tvListaNadaEncontrado.mostrar()
                        binding.tvListaNadaEncontrado.text = "Nenhuma postagem encontrada !"
                    }
                }

                override fun onFailure(call: Call<ArrayList<Postagem>>, t: Throwable) {
                    binding.rvListarPostagem.esconder()
                    binding.tvListaNadaEncontrado.mostrar()
                    binding.tvListaNadaEncontrado.text = "Erro ao realizar a requisição, verifique as configurações da API"
                    Log.e("Repository", "${t.message}")
                    Toast.makeText(binding.root.context , "Failed to get response" , Toast.LENGTH_SHORT).show()
                }

            })
        }
    }*/

    private fun mostarMensagemDeErro404(){
        binding.rvListarPostagem.esconder()
        binding.tvListaNadaEncontrado.mostrar()
        binding.tvListaNadaEncontrado.text = "Nenhuma postagem encontrada !"
    }

}