package br.com.renancsdev.dtiblog.api.builder

import br.com.renancsdev.dtiblog.api.endpoints.Endpoints
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private const val URL_THEMOVIEDB = "http://localhost:8080/"

    private var logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val client = OkHttpClient.Builder().addInterceptor(logging).build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(URL_THEMOVIEDB)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T> builderService(service: Class<T>): T{
        return retrofit.create(service)
    }
}

object ApiClient {
    val apiService: Endpoints by lazy {
        ServiceBuilder.retrofit.create(Endpoints::class.java)
    }
}