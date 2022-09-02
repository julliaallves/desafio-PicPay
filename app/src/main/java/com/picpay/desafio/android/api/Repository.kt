package com.picpay.desafio.android.api

import com.picpay.desafio.android.model.User
import retrofit2.Response

class Repository { //p acessar api

    suspend fun listUsers(): Response<List<User>>{
        return RetrofitInstance.api.listUsers()
    }

    suspend fun addInfos(user: User): Response<User>{
        return RetrofitInstance.api.addInfos(user)
    }

    suspend fun updateInfos(user: User): Response<User>{
        return RetrofitInstance.api.updateInfos(user)
    }

}