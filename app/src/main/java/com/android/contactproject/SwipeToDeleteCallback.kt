package com.android.contactproject

import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.android.contactproject.contactlist.ContactListFragmentAdapter


class SwipeToDeleteCallback(private val context:Context, private val adapter: ContactListFragmentAdapter) :

    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
    //오른쪽만

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        if (direction == ItemTouchHelper.RIGHT) {
            val position = viewHolder.adapterPosition
            val swipedItem = adapter.list[position]

            // 항목을 다시 추가
            adapter.addItem(swipedItem, position)

            // 전화 걸기
            adapter.makePhoneCall(context, position)
        }
    }
}