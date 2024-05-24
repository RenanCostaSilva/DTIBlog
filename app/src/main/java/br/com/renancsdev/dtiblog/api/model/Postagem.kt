package br.com.renancsdev.dtiblog.api.model

import java.util.Date

data class Postagem (
    val id : Long,
    val title : String,
    val postText : String,
    val dateCreationPost : Date
)