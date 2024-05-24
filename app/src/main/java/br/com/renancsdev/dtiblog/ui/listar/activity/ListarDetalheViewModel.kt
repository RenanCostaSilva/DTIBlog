package br.com.renancsdev.dtiblog.ui.listar.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.renancsdev.dtiblog.api.model.Postagem
import br.com.renancsdev.dtiblog.repository.Repository
import br.com.renancsdev.dtiblog.repository.Resultado
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import java.util.ArrayList


class ListarDetalheViewModel(private val repository: Repository , private var id: Long) : ViewModel() {

    //private val _excluirPostagens = MutableLiveData<LiveData<Resultado<ResponseBody?>>>()
    private val _listarPostagem = MutableLiveData<LiveData<Resultado<Postagem?>>>()

    init {
        viewModelScope.launch(IO) {
            //_excluirPostagens.postValue(repository.excluirPostagem(id))
            _listarPostagem.postValue(repository.buscarDetalhePostagem(id))
        }
    }

    fun listarPostagemPorID(id: Long): LiveData<Resultado<Postagem?>> =
        repository.buscarDetalhePostagem(id)

    /*fun excluirPostagemPorID(id: Long): LiveData<Resultado<ResponseBody?>> =
        repository.excluirPostagem(id)*/

}