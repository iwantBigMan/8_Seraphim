package com.android.contactproject.contactlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.contactproject.R
import com.android.contactproject.databinding.ContactListItemBinding

class ContactListFragmentAdapter(private val list:MutableList<UserDataModel>): RecyclerView
.Adapter<ContactListFragmentAdapter.ViewHolder>
    () {

    interface ItemClick {
        fun onClick(view: View, position: Int )
        fun onImageLongClick(view:View, position:Int)
    }
    var itemClick:ItemClick? = null



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

            itemView.setOnLongClickListener {
                itemClick?.onImageLongClick(it, position)
                true
            }
            like.setOnClickListener {
                itemClick?.onClick(it, position)
            }
            if(list[position].isLike){
                binding.mainLike.setImageResource(R.drawable.painted_heart)
            }else{
                binding.mainLike.setImageResource(R.drawable.heart)
            }

        }
        val profile = binding.profileImage
        val like = binding.mainLike
    }
    fun replace(newList: MutableList<UserDataModel>){
        notifyDataSetChanged()
    }
    fun deleteItem(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }
}