package br.com.renancsdev.dtiblog.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DataAtual {

    fun dataSistema(): String {
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
    }

}