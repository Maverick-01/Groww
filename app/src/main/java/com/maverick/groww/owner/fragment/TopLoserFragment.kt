package com.maverick.groww.owner.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.maverick.groww.R
import com.maverick.groww.databinding.FragmentTopLoserBinding
import com.maverick.groww.owner.activity.DetailActivity
import com.maverick.groww.owner.adapter.TopGainerLoserAdapter
import com.maverick.groww.owner.observer.TopGainerLoserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopLoserFragment : Fragment() {

    private lateinit var binding: FragmentTopLoserBinding
    private lateinit var adapter: TopGainerLoserAdapter
    private val viewModel: TopGainerLoserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_top_loser, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTopGainerLoserData()
        viewModel.getTopGainerLoserObservable.observe(viewLifecycleOwner) {
            Log.e("toploser", it.topLosers.toString())
            adapter.submitList(it.topLosers)
        }

        adapter = TopGainerLoserAdapter { item, action ->
            when (action) {
                "onClick" -> {
                    Log.e("itemClicked", item.toString())
                    val intent = Intent(requireContext(), DetailActivity::class.java)
                    intent.putExtra("symbolName",item.ticker)
                    intent.putExtra("price",item.price)
                    intent.putExtra("changePercentage",item.changePercentage)
                    startActivity(intent)
                }
            }
        }.also {
            binding.recyclerView.adapter = it
        }

        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = false
            binding.progress.visibility = View.VISIBLE
            viewModel.getTopGainerLoserData()
        }

        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading) {
                binding.progress.visibility = View.VISIBLE
            } else
                binding.progress.visibility = View.GONE
        }
    }
}