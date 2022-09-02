package com.picpay.desafio.android.adapter

import com.picpay.desafio.android.model.User

interface TaskClickListener { //put da api, oq acontece quando seleciona um user

    fun onTaskClickListener(user: User)

}