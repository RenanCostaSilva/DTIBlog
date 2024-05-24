package br.com.renancsdev.dtiblog.api.endpoints

import br.com.renancsdev.dtiblog.api.model.Postagem
import br.com.renancsdev.dtiblog.model.Posts
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface Endpoints {

    @GET("postagem/buscarPorID/{id}")
    suspend fun buscarPostPorIDLiveData(@Path("id") id: Long): Response<Postagem>

    @GET("postagem/buscarTodos")
    suspend fun buscarTodasPostagensLiveData(): Response<ArrayList<Postagem>>

    @POST("postagem/criar")
    suspend fun criarPostagemLiveData(@Body postagem: Posts): Response<Posts>

    @DELETE("postagem/delete/{id}")
    suspend fun deletarPostPorIDLiveData(@Path("id") id: Long): Response<ResponseBody>

    //
    @GET("postagem/buscarTodos")
     fun getAllPost(): Call<ArrayList<Postagem>>

    @POST("postagem/criar")
     fun criar(@Body postagem: Posts): Call<Posts>

    @GET("postagem/buscarPorID/{id}")
     fun buscarPostPorID(@Path("id") id: Long): Call<Postagem>

    @DELETE("postagem/delete/{id}")
     fun deletarPostPorID(@Path("id") id: Long): Call<ResponseBody>

}