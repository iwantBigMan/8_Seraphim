package com.android.contactproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.contactproject.databinding.FragmentFavoritesBinding

class Favorites : Fragment() {
    private val binding by lazy { FragmentFavoritesBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val titleList = mutableListOf<TitleData>()
       titleList.add(TitleData("즐겨찾기 목록"))

        val RecyclerView = binding.favoritesRecyclerview
        RecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = FavoritesAdapter(titleList,requireContext())
        }
        return binding.root
    }
}
data class TitleData(val title:String)
data class MemberData(val profile: Int, val name: String, val tel: String, var  isfavorites:Boolean = false)