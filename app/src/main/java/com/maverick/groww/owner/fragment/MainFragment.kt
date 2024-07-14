package com.maverick.groww.owner.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.maverick.groww.R
import com.maverick.groww.databinding.FragmentMainBinding
import com.maverick.groww.owner.adapter.ViewPagerAdapter

class MainFragment:Fragment() {
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentList = arrayOf(TopGainerFragment(), TopLoserFragment())
        binding.viewpager.adapter =
            ViewPagerAdapter(fragmentActivity = requireActivity(), fragmentList = fragmentList)
        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
            tab.text = when (position) {
                0 -> "Top Gainer"
                1 -> "Top Loser"
                else -> "Top Gainer"
            }
        }.attach()
    }
}