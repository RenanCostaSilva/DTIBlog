package br.com.renancsdev.dtiblog.di.module

import br.com.renancsdev.dtiblog.api.endpoints.Endpoints
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var redeModule = module{

    // Provide Gson
    single<Gson> {
        GsonBuilder().create()
    }

    // Provide HttpLoggingInterceptor
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    // Provide OkHttpClient
    single {
        OkHttpClient.Builder().
          addInterceptor(get<HttpLoggingInterceptor>().
            setLevel(HttpLoggingInterceptor.Level.BODY))
             .build()
    }

    // Provide Retrofit
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("http://192.168.0.36:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    // Provide GithubApi
    single {
        get<Retrofit>().create(Endpoints::class.java)
    }

}