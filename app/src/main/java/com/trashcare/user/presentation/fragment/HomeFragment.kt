package com.trashcare.user.presentation.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trashcare.user.R
import com.trashcare.user.data.model.trashlist.dummyTrashList
import com.trashcare.user.databinding.FragmentHomeBinding
import com.trashcare.user.presentation.activity.LoginActivity
import com.trashcare.user.presentation.activity.SendTrashActivity
import com.trashcare.user.presentation.adapter.TrashListAdapter
import com.trashcare.user.presentation.viewmodel.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


@Suppress("DEPRECATION")
class HomeFragment : Fragment(

) {

    private var _binding: FragmentHomeBinding?= null
    private val binding get() = _binding!!
    private lateinit var rvTrash: RecyclerView
    private var totalTrash: Int = 0
    private val authViewModel: AuthViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvTrash = binding.rvTrash
        binding.tvTotalTrash.text = getString(R.string.total_pcs_trash_no_value)
        val itemAdapter = TrashListAdapter(dummyTrashList, object : TrashListAdapter.OnTrashAmountChangeListener {
            override fun onTrashAmountChange(totalAmount: Int) {
                binding.tvTotalTrash.text = getString(R.string.total_pcs_trash, totalAmount)
                this@HomeFragment.totalTrash = totalAmount
            }
        })
        rvTrash.adapter = itemAdapter
        rvTrash.layoutManager = LinearLayoutManager(requireActivity())

        binding.btnSendTrash.setOnClickListener {
            val intent = Intent(requireActivity(), SendTrashActivity::class.java)
            intent.putExtra("TOTAL_AMOUNT", totalTrash)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener {
            authViewModel.clearToken()
            authViewModel.clearUserId()
            Toast.makeText(requireActivity(), "Logout berhasil!", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent)
        }
    }
}