package br.com.renancsdev.dtiblog.ui.listar.fragment

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
import java.util.ArrayList

class ListarViewModel(private val repository: Repository) : ViewModel() {

    private val _listPostagens = MutableLiveData<LiveData<Resultado<ArrayList<Postagem>?>>>()
    init {
        viewModelScope.launch(IO) {
            _listPostagens.postValue(repository.buscarTodasPostagens())
        }
    }

    fun buscaListaUsuarios(): LiveData<Resultado<ArrayList<Postagem>?>> =
        repository.buscarTodasPostagens()

}