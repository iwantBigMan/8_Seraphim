package com.android.contactproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.contactproject.databinding.FavoritesItemBinding

class FavoritesItem(private val lesserafim: MutableList<MemberData>) : RecyclerView.Adapter<RecyclerView
.ViewHolder>() {
    interface ItemClick {
        fun onFavoritesClick(view: View, position: Int)
    }

    val itemClick: ItemClick? = null

    inner class FavoritesItemHolder(val binding: FavoritesItemBinding) :
        RecyclerView.ViewHolder
            (binding.root) {
        val profile = binding.favoritesProfile
        val name = binding.favoritesName
        val tel = binding.favoritesTel
        val favorites = binding.favoritesFavorites
    }

    override fun getItemCount(): Int {
        return lesserafim.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       val binding = FavoritesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FavoritesItemHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = lesserafim[position]
        holder as FavoritesItemHolder
        holder.apply {
            profile.setImageResource(item.profile)
            name.text = item.name
            tel.text = item.tel

            favorites.setOnClickListener {
                itemClick?.onFavoritesClick(it,position)
            }
            if(item.isfavorites){
                binding.favoritesFavorites.setImageResource(R.mipmap.paintedstar)
            } else{
                binding.favoritesFavorites.setImageResource(R.mipmap.star)
            }
        }
    }
}