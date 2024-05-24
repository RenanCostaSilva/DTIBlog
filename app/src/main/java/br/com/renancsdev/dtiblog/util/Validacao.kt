package br.com.renancsdev.dtiblog.util

import android.content.Context
import android.widget.EditText
import br.com.renancsdev.dtiblog.extension.toast

class Validacao {

    fun validarPostagem(editTitulo: EditText , editTexto: EditText , context: Context): Boolean{
        return when {
            !editTitulo.text.isNullOrEmpty() && !editTexto.text.isNullOrEmpty() -> {
                true
            }
            else -> {
                if(editTitulo.text.isNullOrBlank() ){
                    context.toast("Título para o post em Branco !")
                } else if(editTexto.text.isNullOrBlank()){
                    context.toast("Texto para o post em Branco !")
                }
                false
            }
        }
    }

}