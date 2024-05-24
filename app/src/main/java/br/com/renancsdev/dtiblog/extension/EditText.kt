package br.com.renancsdev.dtiblog.extension

import android.widget.EditText

fun EditText.limpar(){
    return this.setText("")
}

fun EditText.paraString(): String {
    return this.text.toString()
}