package br.com.renancsdev.dtiblog.model

import java.util.Date

data class Post(
    val id : Long,
    val title : String,
    val postText : String,
    val dateCreationPost : Date
)
