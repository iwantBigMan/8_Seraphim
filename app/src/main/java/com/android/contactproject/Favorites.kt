package com.android.contactproject

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.contactproject.databinding.FragmentFavoritesBinding

class Favorites : Fragment() {
    private val binding by lazy { FragmentFavoritesBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val lesserafim = mapOf(
            "친구 목록" to mutableListOf(
                MemberData(R.drawable.character10, "사쿠라", "010-1234-5678"),
                MemberData(R.drawable.character1, "권은비", "010-1111-1111"),
                MemberData(R.drawable.character2, "강혜원", "010-2222-2222"),
                MemberData(R.drawable.character3, "최예나", "010-3333-3333"),
                MemberData(R.drawable.character4, "이채연", "010-4444-4444"),
                MemberData(R.drawable.character5, "김채원", "010-5555-5555"),
                MemberData(R.drawable.character6, "김민주", "010-6666-6666"),
                MemberData(R.drawable.character7, "조유리", "010-7777-7777"),
                MemberData(R.drawable.character8, "안유진", "010-8888-8888"),
                MemberData(R.drawable.character9, "장원영", "010-9999-9999"),
                MemberData(R.drawable.character11, "나코", "010-1357-1357"),
                MemberData(R.drawable.character12, "히토미", "010-2468-2468")
            )
        )
        binding.favoritesTitle.text = lesserafim.keys.elementAt(0)
        binding.favoritesRecyclerview.apply {
            adapter = FavoritesAdapter(lesserafim).apply {
                itemClick = object :FavoritesAdapter.ItemClick{
                    override fun onFavoritesClick(view: View, position: Int) {
                        val item = lesserafim.values.flatten()[position]
                        val valuesList = lesserafim.values.find { list ->
                            list.any { it == item }
                        }
                        val builder = AlertDialog.Builder(context)
                        builder.setTitle("즐겨찾기 해제")
                        builder.setMessage("즐겨찾기를 해제 하시겠읍니까?")

                        val listener = object : DialogInterface.OnClickListener {
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                when(which){
                                    DialogInterface.BUTTON_POSITIVE ->{
                                        item.isfavorites = !item.isfavorites
                                        valuesList?.remove(item)
                                       notifyDataSetChanged()
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
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }


        val searchView = binding.favoritesSearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }
        })

        return binding.root
    }
}

data class MemberData(
    val profile: Int,
    val name: String,
    val tel: String,
    var isfavorites: Boolean = false
)