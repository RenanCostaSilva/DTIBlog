package br.com.renancsdev.dtiblog.ui.listar.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import br.com.renancsdev.dtiblog.MainActivity
import br.com.renancsdev.dtiblog.R
import br.com.renancsdev.dtiblog.api.builder.ApiClient
import br.com.renancsdev.dtiblog.api.model.Postagem
import br.com.renancsdev.dtiblog.databinding.ActivityListarDetalheBinding
import br.com.renancsdev.dtiblog.extension.formatePTBR
import br.com.renancsdev.dtiblog.extension.limpar
import br.com.renancsdev.dtiblog.extension.toast
import br.com.renancsdev.dtiblog.repository.Resultado
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListarDetalheActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListarDetalheBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configurarBindingLayout()
        configurarBottomNavigation()
        mostarDados()
        excluirPostagem()
        voltarTelaPrincipal()
    }

    private fun configurarBindingLayout(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_listar_detalhe)
        setContentView(binding.root)
    }
    private fun configurarBottomNavigation(){
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    private fun buscarIdIntent() = intent.getLongExtra("id", 0L)

    private fun mostarDados(){
        listarPostagem(buscarIdIntent())
    }

    private fun excluirPostagem(){
        binding.btnFragDeletarPost.setOnClickListener {
            listarPostagem(buscarIdIntent())
            excluirPostagem(buscarIdIntent())
        }
    }

    private fun voltarTelaPrincipal(){
        binding.btnFragVoltarPost.setOnClickListener {
            binding.root.context.startActivity(Intent(binding.root.context, MainActivity::class.java))
        }
    }


    private fun listarPostagem(id: Long){
        CoroutineScope(Dispatchers.IO).launch {
            chamadaApiListar(id)
        }
    }

    private fun excluirPostagem(id: Long){
        CoroutineScope(Dispatchers.IO).launch {
            chamadaAPiDeletar(id)
        }
    }

    private fun chamadaApiListar(id: Long){
        ApiClient.apiService.buscarPostPorID(id).apply {
            enqueue(object : Callback<Postagem> {

                override fun onResponse(call: Call<Postagem>, response: Response<Postagem>) {
                    if(response.code() == 200){
                        mostarDadosDaLista(response)
                    }
                }

                override fun onFailure(call: Call<Postagem>, t: Throwable) {
                    Log.e("Repository", "${t.message}")
                    Toast.makeText(binding.root.context , "Failed to get response" , Toast.LENGTH_SHORT).show()
                }

            })
        }
    }

    private fun chamadaAPiDeletar(id: Long){
        ApiClient.apiService.deletarPostPorID(id).apply {
            enqueue(object : Callback<ResponseBody> {

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if(response.code() == 200 || response.code() == 201 || response.code() == 202 || response.code() == 203){
                        binding.root.context.toast("Postagem deletada com sucesso !")
                        finish()
                        limparCampos()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("Repository", "${t.message}")
                    binding.root.context.toast("Failed to get response")
                }

            })
        }
    }

    private fun limparCampos(){
        binding.tvTitleListar.limpar()
        binding.tvItemPostData.limpar()
        binding.tvItemPostTexto.limpar()
    }


    private fun mostarDadosDaLista(response: Response<Postagem>){
        binding.tvItemPostTittle.text = response.body()?.title
        binding.tvItemPostData.text = response.body()?.dateCreationPost!!.formatePTBR()
        binding.tvItemPostTexto.text = response.body()?.postText
    }

}