package com.maverick.groww.owner.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.maverick.groww.owner.fragment.TopGainerFragment
import com.maverick.groww.owner.fragment.TopLoserFragment

class ViewPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val fragmentList: Array<Fragment>
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return TopGainerFragment()
            1 -> return TopLoserFragment()
        }
        return TopGainerFragment()
    }

}