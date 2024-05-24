package br.com.renancsdev.dtiblog.ui.criar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.renancsdev.dtiblog.api.model.Postagem
import br.com.renancsdev.dtiblog.model.Posts
import br.com.renancsdev.dtiblog.repository.Repository
import br.com.renancsdev.dtiblog.repository.Resultado
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.ArrayList

class CriarViewModel(private val repository: Repository , private val postagem: Posts) : ViewModel() {

    private val _criarPostagens = MutableLiveData<LiveData<Resultado<Posts?>>>()
    init {
        viewModelScope.launch(IO) {
            _criarPostagens.postValue(repository.criarPostagem(postagem))
        }
    }

    fun criarPostagem(postagem: Posts): LiveData<Resultado<Posts?>> =
        repository.criarPostagem(postagem)

}