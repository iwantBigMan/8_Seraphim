package com.android.contactproject

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.android.contactproject.contactlist.ContactListFragmentAdapter

class SwipeToDeleteCallback(private val adapter: ContactListFragmentAdapter) :

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
            adapter.deleteItem(position)
        }
    }
}