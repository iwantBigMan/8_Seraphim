package com.android.contactproject.contactlist

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.contactproject.Manifest
import com.android.contactproject.R
import com.android.contactproject.databinding.ContactListItemBinding



class ContactListFragmentAdapter(private val list: MutableList<UserDataModel>) : RecyclerView
.Adapter<ContactListFragmentAdapter.ViewHolder>
    () {

    interface ItemClick {
        fun onClick(view: View, position: Int)
        fun onImageLongClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null


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
            if (list[position].isLike) {
                binding.mainLike.setImageResource(R.drawable.painted_heart)
            } else {
                binding.mainLike.setImageResource(R.drawable.heart)
            }

        }


        val profile = binding.profileImage
        val like = binding.mainLike

    }

    fun replace(newList: MutableList<UserDataModel>) {
        notifyDataSetChanged()
    }
    fun makePhoneCall(position: Int) {
        val phoneNumber = list[position].ph
        // 전화를 걸기 위한 Intent를 생성
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$phoneNumber")

        // CALL_PHONE 권한이 있는지 확인
        if (ContextCompat.checkSelfPermission(
                itemView.context,
                Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            itemView.context.startActivity(intent) // 전화 걸기
        } else {
//        list.removeAt(position)
//        notifyItemRemoved(position)

        }


    }


}