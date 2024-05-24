package br.com.renancsdev.dtiblog.extension

import android.view.View
import android.widget.ProgressBar

fun ProgressBar.mostar(){
    this.visibility = View.VISIBLE
}

fun ProgressBar.esconder(){
    this.visibility = View.GONE
}