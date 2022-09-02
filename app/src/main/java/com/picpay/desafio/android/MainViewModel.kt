package com.picpay.desafio.android

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.api.Repository
import com.picpay.desafio.android.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
    ): ViewModel() {

    var infoSelecionada: User? = null

    private val _myUserResponse =
        MutableLiveData<Response<List<User>>>() //troca infos

    val myUserResponse: LiveData<Response<List<User>>> =
        _myUserResponse //mostra infos, p não permitir mudanças

    init{
        listUser()
    }


    fun addInfo(user: User){
        viewModelScope.launch {
            try {
                val response = repository.addInfos(user)
                Log.d("Opa",response.body().toString())
                listUser()
            }catch (e: Exception){
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun listUser(){ //trazendo dados da api enquanto app é executado
        viewModelScope.launch {
            try { //p evitar q programa quebre
                val response = repository.listUsers() //colocando na val p se caso o programa quebrar
                _myUserResponse.value = response
            }catch (e:Exception){
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun updateInfos(user: User){
        viewModelScope.launch {
            try {
                repository.updateInfos(user)
                listUser()
            }catch (e:Exception){
                Log.d("Erro", e.message.toString())
            }
        }
    }

}