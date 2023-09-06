package com.android.contactproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.contactproject.contactlist.UserDataModel
import com.android.contactproject.databinding.FavoritesAdapterBinding

class FavoritesAdapter(
    private val lesserafim:MutableList<UserDataModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface ItemClick {
        fun onFavoritesClick(view: View, position: Int)
    }
    var itemClick:ItemClick? = null

    inner class FavoritesAdapterHolder(private val binding: FavoritesAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(member: UserDataModel) {
            binding.apply {
                favoritesProfile.setImageResource(member.userImage)
                favoritesName.text = member.name
                favoritesTel.text = member.ph

                favorites.setOnClickListener {
                    itemClick?.onFavoritesClick(it, position)
                }
                if(member.isLike){
                    binding.favoritesFavorites.setImageResource(R.drawable.painted_heart)
                }else{
                    binding.favoritesFavorites.setImageResource(R.drawable.heart)
                }
            }
        }

        val favorites = binding.favoritesFavorites
    }

    override fun getItemCount(): Int {
        return lesserafim.size
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
        val member = lesserafim[position]
        holder.bind(member)
    }
}