package com.picpay.desafio.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.MainViewModel
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.model.User
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_form.view.*

class UserAdapter(
    val taskClickListener: TaskClickListener,
    val mainViewModel: MainViewModel
) : RecyclerView.Adapter<UserAdapter.UserListItemVH>() {

    private var listusers = emptyList<User>() //lista vazia

    class UserListItemVH(val binding: ListItemUserBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserListItemVH { //cria item c base no layout
        return UserListItemVH(
            ListItemUserBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: UserListItemVH, position: Int) { //processa dados
        val users = listusers[position]

        holder.binding.textName.text = users.name
        holder.binding.textUsername.text = users.username
        //holder.binding.imageProfile.setImageDrawable() como colocar img?

        holder.itemView.setOnClickListener {
            taskClickListener.onTaskClickListener(users)
        }

       /* holder.binding.buttonDeletar.setOnClickListener{ TODO: CRIAR BOTAO
            mainViewModel.updateUser(user)
        }
        */

    }

    override fun getItemCount(): Int {
        return listusers.size
    }

    fun setList(list: List<User>) { //metodo p mostrar qnts vezes aparece
        listusers = list.sortedByDescending { it.id } //p deixar cadastros mais antigos por ultimo
        notifyDataSetChanged() //p mudar conforme atualiza
    }
}