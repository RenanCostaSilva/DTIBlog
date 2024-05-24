package br.com.renancsdev.dtiblog.extension

import java.util.Date
import java.util.Locale

fun Date.formatePTBR(): String =
    android.icu.text.SimpleDateFormat("dd/MM/yyyy", Locale("pt-br", "America/Sao_Paulo"))
        .format(this)