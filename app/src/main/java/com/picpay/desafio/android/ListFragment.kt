package com.picpay.desafio.android

import android.hardware.usb.UsbRequest
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.adapter.TaskClickListener
import com.picpay.desafio.android.adapter.UserAdapter
import com.picpay.desafio.android.databinding.FragmentListBinding
import com.picpay.desafio.android.model.User
import kotlinx.android.synthetic.main.fragment_list.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListFragment : Fragment(), TaskClickListener {

    private lateinit var binding: FragmentListBinding
    private val mainViewModel: MainViewModel by activityViewModels() //p sobreviver em tds telas

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentListBinding.inflate(layoutInflater, container, false)

        // mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val listInfos = listOf(
            User(
                "Tyrell",
                "Ms. Ignacio Reilly",
                "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/153.jpg",
                1
            ),
            User(
                "Enid",
                "Brendan Gleichner",
                "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/589.jpg",
                2
            ),
            User(
                "Fabian",
                "Allen Harris",
                "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/1173.jpg",
                3
            ),
            User(
                "Asa",
                "Sheri Koepp",
                "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/758.jpg",
                4
            ),
            User(
                "Sage",
                "Sandra Abernathy",
                "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/541.jpg",
                5
            ),
            User(
                "Zena",
                "Kathy Simonis",
                "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/1237.jpg",
                6
            ),
            User(
                "Alexander",
                "Elijah Walter",
                "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/296.jpg",
                7
            ),
            User(
                "Dustin",
                "Pearl Heller V",
                "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/980.jpg",
                8
            ),
            User(
                "Cathryn",
                "Emma Swaniawski",
                "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/336.jpg",
                9
            ),
            User(
                "Leslie",
                "Leland Reilly",
                "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/208.jpg",
                10
            ),
            User(
                "Nestor",
                "Billy Shields",
                "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/509.jpg",
                11
            ),
            User(
                "Joy",
                "Ms. Bernard Bosco",
                "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/340.jpg",
                12
            ),
            User(
                "Santiago",
                "Alonzo Cummerata",
                "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/622.jpg",
                13
            ),
            User(
                "Federico",
                "Sonja Hartmann",
                "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/425.jpg",
                14
            ),
            User(
                "Kimberly",
                "Teri Kub",
                "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/615.jpg",
                15
            ),
            User(
                "Sheldon",
                "Mabel Huel",
                "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/589.jpg",
                16
            ),
            User(
                "Duncan",
                "Joe D'Amore",
                "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/412.jpg",
                17
            ),
            User(
                "Cheyanne",
                "Jeff Lynch",
                "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/173.jpg",
                18
            ),
            User(
                "Cassie",
                "Tom Bins",
                "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/408.jpg",
                19
            ),
            User(
                "Jalyn",
                "Ms. William Walker",
                "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/153.jpg",
                20
            ),
            User(
                "Antonina",
                "Marion Daniel",
                "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/29.jpg",
                21
            ),
            User(
                "Lindsey",
                "Mike Von",
                "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/75.jpg",
                22
            ),
            User(
                "Kaylin",
                "Mrs. Oliver Shields",
                "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/1087.jpg",
                23
            ),
            User(
                "Cordell",
                "Micheal Gutmann",
                "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/1082.jpg",
                24
            ),
            User(
                "Bettie",
                "Miss Jan Ritchie",
                "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/804.jpg",
                25
            )
        )

        mainViewModel.listUser()

        //recyclerview
        val adapter = UserAdapter(this, mainViewModel)
        binding.recyclerViewUsers.layoutManager =
            LinearLayoutManager(context) //um item ambaixo do outro
        binding.recyclerViewUsers.adapter = adapter
        binding.recyclerViewUsers.setHasFixedSize(true) //tamanho fixo

        adapter.setList(listInfos)

        // pegar do live datamainViewModel.selectUsers

        binding.buttonAdd.setOnClickListener {
            mainViewModel.infoSelecionada = null //p vir nulo quando for add novo user
            findNavController().navigate(R.id.action_listFragment_to_formFragment)
        }

        mainViewModel.myUserResponse.observe(viewLifecycleOwner) { response ->
            if (response.body() != null) {
                adapter.setList(response.body()!!)
            }
        }

        return binding.root
    }

    override fun onTaskClickListener(user: User) {
        mainViewModel.infoSelecionada = user
        findNavController().navigate(R.id.action_listFragment_to_formFragment)
    }
}