package com.android.contactproject

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.contactproject.databinding.ContactListItemBinding
import java.text.DecimalFormat

class ContactListFragmentAdapter: RecyclerView.Adapter<ContactListFragmentAdapter.ViewHolder>() {
    interface ItemLongClick {
        fun onLongClick(view: View, position: Int )

    }
    interface ItemClick {
        fun onClick(view: View, position: Int )
    }


    private val list = ArrayList<UserDataModel>()

    fun addItem(phoneNumberModel: UserDataModel) {
        if (phoneNumberModel == null) {
            return
        }

        list.add(phoneNumberModel)
        notifyItemChanged(list.size - 1)
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
        val phNumber = list[position].ph
        holder.bind(item)
        holder.phNumber.text = DecimalFormat("###-####-####").format(phNumber)




    }

    inner class ViewHolder(
        private val binding: ContactListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val phNumber = binding.phNumber


        fun bind(item: UserDataModel) = with(binding) {
           // 데이터
            name.text = item.name
        }

    }

}