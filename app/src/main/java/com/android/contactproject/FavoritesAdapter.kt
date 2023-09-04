package com.android.contactproject

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.contactproject.databinding.FavoritesAdapterBinding

class FavoritesAdapter(
    private val titleList: MutableList<TitleData>,
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var lesserafim = mutableListOf<MemberData>()

    inner class FavoritesAdapterHolder(private val binding: FavoritesAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val title = binding.favoritesTitle
        val recyclerView = binding.favoritesRecyclerview
    }

    override fun getItemCount(): Int {
        return titleList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = FavoritesAdapterBinding.inflate(LayoutInflater.from(parent.context),
            parent,false)
        return FavoritesAdapterHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as FavoritesAdapterHolder
//        lesserafim = ContactListFragmentAdapter.
        lesserafim.add(MemberData(R.drawable.character11,"김채원","010-5555-5555"))
        val item = titleList[position]
        val layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        val adapter = FavoritesItem(lesserafim)
        holder.apply {
            recyclerView.layoutManager = layoutManager
            recyclerView.setHasFixedSize(true)
            title.text = item.title
            recyclerView.adapter = adapter
        }
        adapter.itemClick = object :FavoritesItem.ItemClick{
            override fun onFavoritesClick(view: View, position: Int) {
                val item = lesserafim[position]
                val builder = AlertDialog.Builder(context)
                builder.setTitle("즐겨찾기 해제")
                builder.setMessage("즐겨찾기를 해제 하시겠읍니까?")

                val listener = object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        when(which){
                            DialogInterface.BUTTON_POSITIVE ->{
                                item.isfavorites = !item.isfavorites
                                lesserafim.remove(item)
                                adapter.notifyDataSetChanged()
                            }
                            DialogInterface.BUTTON_NEGATIVE -> {
                                dialog?.dismiss()
                            }
                        }
                    }
                }
                builder.setPositiveButton("확인",listener)
                builder.setNegativeButton("취소",listener)

                builder.show()
            }
        }
    }
}