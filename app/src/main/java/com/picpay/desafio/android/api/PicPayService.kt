package com.picpay.desafio.android.api

import com.picpay.desafio.android.model.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface PicPayService { //onde coloca verbos http

    @GET("users")
    suspend fun listUsers(): Response<List<User>> //traz resposta da api

    @POST("infos")
    suspend fun addInfos( //suspend p poder ser rodada dentro de corrotina
        @Body user: User
    ): Response<User>

    @PUT ("infos")
    suspend fun updateInfos(
        @Body user: User
    ): Response<User>

    @DELETE ("users/{id}")
    suspend fun deleteUser(
        @Path("id") id: Long
    ): Response<User>

}

