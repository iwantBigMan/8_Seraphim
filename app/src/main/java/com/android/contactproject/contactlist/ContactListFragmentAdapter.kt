package com.android.contactproject.contactlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.contactproject.databinding.ContactListItemBinding

class ContactListFragmentAdapter: RecyclerView.Adapter<ContactListFragmentAdapter.ViewHolder>() {
//    interface ItemLongClick {
//        fun onLongClick(view: View, position: Int )
//
//    }
//    interface ItemClick {
//        fun onClick(view: View, position: Int )
//    }


    private var list = mutableListOf<UserDataModel>()

//    fun addItem(phoneNumberModel: UserDataModel) {
//        if (phoneNumberModel == null) {
//            return
//        }
//
//        list.add(phoneNumberModel)
//        notifyItemChanged(list.size - 1)
//    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ContactListItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)





    }

    inner class ViewHolder(
        private val binding: ContactListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {



        fun bind(item: UserDataModel) = with(binding) {
           // 데이터
            userName.text = item.name
            phNumber.text = item.ph
            profileImage.setImageResource(list[position].userImage)
            mainLike.setImageResource(list[position].isLike)

        }

    }
    fun replace(newList: MutableList<UserDataModel>){
        list = newList.toMutableList()
        notifyDataSetChanged()
    }

}