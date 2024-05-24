package br.com.renancsdev.dtiblog.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.liveData
import br.com.renancsdev.dtiblog.api.builder.ApiClient
import br.com.renancsdev.dtiblog.api.endpoints.Endpoints
import br.com.renancsdev.dtiblog.model.Posts
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(private val endpoints: Endpoints) {

    fun buscarDetalhePostagem(id: Long) = liveData {

        val resposta = endpoints.buscarPostPorIDLiveData(id)
        if(resposta.isSuccessful){
            emit(Resultado.Sucesso(dado = resposta.body()))
        }else {
            emit(Resultado.Erro(exception = Exception("Falha ao buscar os dados da postagem")))
        }

    }

    fun buscarTodasPostagens() = liveData {

        val resposta = endpoints.buscarTodasPostagensLiveData()
        if(resposta.isSuccessful){
            if(resposta.code() == 200){
                emit(Resultado.Sucesso(dado = resposta.body()))
            }else if(resposta.code() == 404) {
                emit(Resultado.Erro(exception = Exception("Erro ao realizar a requisição. Dados da requisição não encontrada")))
            }
        }else {
            emit(Resultado.Erro(exception = Exception("Falha ao buscar os dados da postagem")))
        }

    }

    fun criarPostagem(postagem: Posts) = liveData {

        val resposta = endpoints.criarPostagemLiveData(postagem)
        if (resposta.isSuccessful) {
            emit(Resultado.Sucesso(dado = resposta.body()))
        } else {
            emit(Resultado.Erro(exception = Exception("Falha ao buscar os dados da postagem")))
        }

    }

    fun excluirPostagem(id: Long) = liveData {

        val resposta = endpoints.deletarPostPorIDLiveData(id)
        if (resposta.isSuccessful) {
            if(resposta.code() == 200 || resposta.code() == 201 || resposta.code() == 202 || resposta.code() == 203){
                emit(Resultado.Sucesso(dado = resposta.body()))
            }else if(resposta.code() == 404) {
                emit(Resultado.Erro(exception = Exception("Erro ao realizar a requisição. Dados da requisição não encontrada")))
            }
        } else {
            emit(Resultado.Erro(exception = Exception("Falha ao buscar os dados da postagem")))
        }

    }

}