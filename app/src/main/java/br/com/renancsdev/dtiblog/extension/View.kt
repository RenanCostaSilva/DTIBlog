package br.com.renancsdev.dtiblog.extension

import android.view.View

fun View.esconder(){
    this.visibility = View.GONE
}

fun View.mostrar(){
    this.visibility = View.VISIBLE
}