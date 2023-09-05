package com.android.contactproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.contactproject.databinding.FavoritesAdapterBinding

class FavoritesAdapter(
    private val lesserafim: Map<String, MutableList<MemberData>>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface ItemClick {
        fun onFavoritesClick(view: View, position: Int)
    }
    var itemClick:ItemClick? = null

    inner class FavoritesAdapterHolder(private val binding: FavoritesAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(member: MemberData) {
            binding.apply {
                favoritesProfile.setImageResource(member.profile)
                favoritesName.text = member.name
                favoritesTel.text = member.tel

                favorites.setOnClickListener {
                    itemClick?.onFavoritesClick(it, position)
                }
                if(member.isfavorites){
                    binding.favoritesFavorites.setImageResource(R.mipmap.paintedstar)
                }else{
                    binding.favoritesFavorites.setImageResource(R.mipmap.star)
                }
            }
        }

        val favorites = binding.favoritesFavorites
    }

    override fun getItemCount(): Int {
        return lesserafim.values.sumOf { it.size }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = FavoritesAdapterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return FavoritesAdapterHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as FavoritesAdapterHolder
        val member = lesserafim.values.flatten()[position]
        holder.bind(member)
    }
}