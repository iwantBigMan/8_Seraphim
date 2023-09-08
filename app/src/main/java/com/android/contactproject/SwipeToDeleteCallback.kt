package com.android.contactproject

import android.content.Context
import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.contactproject.contactlist.ContactListFragmentAdapter


class SwipeToDeleteCallback(private val context:Context, private val adapter: ContactListFragmentAdapter) :

    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
    //오른쪽만
    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        getDefaultUIUtil().clearView(getView(viewHolder))
    }
    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            val view = getView(viewHolder)

            getDefaultUIUtil().onDraw(
                c,
                recyclerView,
                view,
                dX,
                dY,
                actionState,
                isCurrentlyActive
            )
        }
    }
    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        viewHolder?.let {
            getDefaultUIUtil().onSelected(getView(it))
        }
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: ViewHolder,
        target: ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
        if (direction == ItemTouchHelper.RIGHT) {
            val position = viewHolder.adapterPosition
            val swipedItem = adapter.list[position]

            // 항목을 다시 추가
//            adapter.addItem(swipedItem, position)

            // 전화 걸기
            adapter.makePhoneCall(context, position)
            adapter.notifyItemChanged(position)
        }
    }
    private fun getView(viewHolder: ViewHolder): View {
        return (viewHolder as ContactListFragmentAdapter.ContactViewHolder).itemView.findViewById(R.id.swipe_view)
    }
}
