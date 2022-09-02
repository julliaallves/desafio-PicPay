package com.picpay.desafio.android.api

import com.picpay.desafio.android.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance { //objt pq só quero criar 1x dentro do projeto
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL) // nunca vai mudar o url, é mais seguro
            .addConverterFactory(GsonConverterFactory.create())//consegue converter json p kotlin ler
            .build()
    }

    val api: PicPayService by lazy { //p pegar os metodos
        retrofit.create(PicPayService::class.java) //create q retorna a api
    }

}