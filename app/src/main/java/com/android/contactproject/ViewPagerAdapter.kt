package com.android.contactproject


import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.contactproject.contactlist.ContactListFragment

class ViewPagerAdapter(fragmentActivity:FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: kotlin.Int): androidx.fragment.app.Fragment {
        return when(position){
            0 -> ContactListFragment()
            1 -> Favorites()
            else -> MyPageFragment()
        }
    }
    override fun getItemCount():Int =3
}