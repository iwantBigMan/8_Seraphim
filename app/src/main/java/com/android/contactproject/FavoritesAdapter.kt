package com.android.contactproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.contactproject.contactlist.UserDataModel
import com.android.contactproject.databinding.FavoritesAdapterBinding
import com.android.contactproject.databinding.GridTypeItemBinding

class FavoritesAdapter(
    private val lesserafim:MutableList<UserDataModel>,
    private var viewType: Int
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
    inner class FavoritesGridAdapterHolder(private val binding:GridTypeItemBinding): RecyclerView
    .ViewHolder(binding.root){
        fun bind(member:UserDataModel) {
            binding.apply {
                GridName.text = member.name
                GridProfile.setImageResource(member.userImage)
            }
        }
    }


    override fun getItemCount(): Int {
        return lesserafim.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == listViewType) {
            val binding = FavoritesAdapterBinding.inflate(inflater, parent, false)
            FavoritesAdapterHolder(binding)
        } else {
            val binding = GridTypeItemBinding.inflate(inflater, parent, false)
            FavoritesGridAdapterHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FavoritesAdapterHolder && viewType == listViewType) {
            val member = lesserafim[position]
            holder.bind(member)
        } else if (holder is FavoritesGridAdapterHolder && viewType == gridViewType) {
            val member = lesserafim[position]
            holder.bind(member)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return viewType
    }
    companion object {
        val listViewType = 2
        val gridViewType = 3
    }
}