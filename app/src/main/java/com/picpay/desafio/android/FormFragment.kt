package com.picpay.desafio.android

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.picpay.desafio.android.databinding.FragmentFormBinding
import com.picpay.desafio.android.model.User

class FormFragment : Fragment() {

    private lateinit var binding: FragmentFormBinding
    private val mainViewModel: MainViewModel by activityViewModels() //p sobreviver em todas as telas
   // private var cadastro: User? = null
   private var infoSelecionada: User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentFormBinding.inflate(layoutInflater, container, false)

        carregarDados()

        mainViewModel.listUser()

        mainViewModel.myUserResponse.observe(viewLifecycleOwner){
            Log.d("Requisição", it.body().toString())
        }

        binding.buttonConfirmar.setOnClickListener{
            inserirNoBanco()
        }

        return binding.root
    }

    private fun validarCampos (
        name: String, username: String, img: String): Boolean{

        return !(
                (name == "" || name.length < 3 || name.length > 20) ||
                        (username == "" || username.length < 5 || username.length > 50) ||
                        (img == "" || img.length < 3 || img.length > 200)
                )
    }

    private fun inserirNoBanco(){
        val name = binding.textNome.text.toString()
        val username = binding.textUser.text.toString()
        val img = binding.textImgLink.text.toString()

        if(validarCampos(name, username, img)){
            val salvar: String

            if (infoSelecionada != null){
                salvar = "Informações atualizadas"
                val user = User(name,username,img,infoSelecionada?.id!!)
                mainViewModel.addInfo(user)

            }else{
                salvar = "Usuário adicionado"
                val user = User(name, username, img, 0)
                mainViewModel.addInfo(user)
            }
            Toast.makeText(context, salvar, Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_formFragment_to_listFragment)

        }else{
            Toast.makeText(context,"Verifique os campos", Toast.LENGTH_SHORT).show()
        }
    }
    private fun carregarDados(){
        infoSelecionada = mainViewModel.infoSelecionada
        if (infoSelecionada != null){
            binding.textNome.setText(infoSelecionada?.name)
            binding.textUser.setText(infoSelecionada?.username)
            binding.textImgLink.setText(infoSelecionada?.img)
        }
    }

}