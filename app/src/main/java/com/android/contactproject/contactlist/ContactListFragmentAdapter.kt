package com.android.contactproject.contactlist

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.contactproject.R
import com.android.contactproject.databinding.ContactListItemBinding



class ContactListFragmentAdapter(val list: MutableList<UserDataModel>) : RecyclerView
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

    companion object {
        const val CALL_PHONE_PERMISSION_REQUEST_CODE = 123
    }

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
    fun makePhoneCall(context : Context, position: Int) {
        val phoneNumber = list[position].ph
        // 전화를 걸기 위한 Intent를 생성
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$phoneNumber")

        // CALL_PHONE 권한이 있는지 확인
        if (ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            context.startActivity(intent) // 전화 걸기
        } else {
            // 권한이 없는 경우 권한 요청 로직
            ActivityCompat.requestPermissions(
                context as Activity, // context를 Activity로 캐스팅
                arrayOf(android.Manifest.permission.CALL_PHONE),
                CALL_PHONE_PERMISSION_REQUEST_CODE)
        }
    }
    fun addItem(phoneNumberModel: UserDataModel, position: Int) {
        if (position >= 0 && position <= list.size) {
            list.add(position, phoneNumberModel)
            notifyItemInserted(position)
        }
    }
    }