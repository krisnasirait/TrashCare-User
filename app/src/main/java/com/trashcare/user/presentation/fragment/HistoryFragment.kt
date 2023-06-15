package com.trashcare.user.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.trashcare.user.databinding.FragmentHistoryBinding
import com.trashcare.user.presentation.adapter.HistoryListAdapter
import com.trashcare.user.presentation.viewmodel.HistoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding?= null
    private val binding get() = _binding!!
    private lateinit var userId: String
    private val historyViewModel: HistoryViewModel by viewModel()
    private lateinit var historyListAdapter: HistoryListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val getUserId = historyViewModel.getUserId()
        userId = getUserId ?: ""

        setupRecyclerView()
        setupViewModelObservers()


    }

    private fun setupRecyclerView() {
        historyListAdapter = HistoryListAdapter()
        binding.rvHistory.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = historyListAdapter
        }

        if (historyListAdapter.itemCount == 0) {
            binding.noData.visibility = View.VISIBLE
        } else {
            binding.noData.visibility = View.GONE
        }

    }

    private fun setupViewModelObservers() {
        historyViewModel.getHistory(userId)

        historyViewModel.historyUser.observe(viewLifecycleOwner){ data ->
            historyListAdapter.setData(data)

            if (data.isEmpty()) {
                binding.noData.visibility = View.VISIBLE
            } else {
                binding.noData.visibility = View.GONE
            }
        }
    }

}