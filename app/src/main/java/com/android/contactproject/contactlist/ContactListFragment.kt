package com.android.contactproject.contactlist


import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.contactproject.AddContactDialogFragment
import com.android.contactproject.AddMemberData
import com.android.contactproject.R
//import com.android.contactproject.SwipeToDeleteCallback
import com.android.contactproject.databinding.ContactListFragmentBinding
import com.android.contactproject.detailPage.ContactDetailActivity
import org.w3c.dom.Text


class ContactListFragment : Fragment() {

    private var list = mutableListOf<UserDataModel>()

    private var _binding: ContactListFragmentBinding? = null
    private val binding get() = _binding!!
    val listArray = arrayListOf<UserDataModel>()
    private val listAdapter by lazy {
        ContactListFragmentAdapter(list).apply {
            itemClick = object : ContactListFragmentAdapter.ItemClick {
                override fun onClick(view: View, position: Int) {
                    val item = list[position]
                    val builder = AlertDialog.Builder(requireContext())
                    builder.setTitle("즐겨찾기")
                    builder.setMessage("즐겨찾기를 하시겠읍니까?")
                    Log.d("ContactProjects", "되돌아왔을떄 데이터 : ${item}")
                    val listener = object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            when (which) {
                                DialogInterface.BUTTON_POSITIVE -> {
                                    item.isLike = !item.isLike
                                    if (item.isLike) {
                                        list.removeAt(position)
                                        list.add(0, item)
                                        if (!listArray.contains(item)) {
                                            listArray.add(item)
                                        }
                                    } else {
                                        listArray.remove(item)
                                    }
                                    notifyDataSetChanged()
                                    val bundle = Bundle()
                                    bundle.putParcelableArrayList("ToFavorites", listArray)
                                    setFragmentResult("ToFavoritesKey", bundle)
                                }

                                DialogInterface.BUTTON_NEGATIVE -> {
                                    dialog?.dismiss()
                                }
                            }
                        }
                    }
                    builder.setPositiveButton("확인", listener)
                    builder.setNegativeButton("취소", listener)

                    builder.show()
                }

//              override fun onImageLongClick(view: View, position: Int) {
//                  val bundle = Bundle()
//                  val item = list[position]
//                  bundle.putParcelable("UserData", item)
//                  val transaction = requireActivity().supportFragmentManager.beginTransaction()
//                  val contactDetailFragment = ContactDetailFragment()
//                 contactDetailFragment.arguments = bundle
//                  transaction.replace(R.id.main_layout, contactDetailFragment)
//                  transaction.commit()
//              }

                override fun onImageLongClick(view: View, position: Int) {
                    val item = list[position]

                    // 데이터를 Intent에 추가
                    val intent = Intent(requireContext(), ContactDetailActivity::class.java)
                    intent.putExtra("userData", item)

                    // ContactDetailActivity를 시작
                    startActivity(intent)
                }

            }

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.contact_list_fragment, container, false)
        /*val dialogImage = view.findViewById<ImageView>(R.id.)
        val dialogName = view.findViewById<TextView>(R.id.dialog_name)
        val dialogPhone = view.findViewById<TextView>(R.id.dialog_phone)*/
        /*arguments?.let {
            val image = it.getInt("imageUri")
            val name = it.getString("name")
            val phone = it.getString("phone")

            dialogImage.setImageResource(image)
            dialogName.text = name
            dialogPhone.text = phone
        }*/
        parentFragmentManager.setFragmentResultListener(
            "ToContactListKey",
            this
        ) { key, result ->
            val getFavorites = result.getParcelableArrayList<UserDataModel>("ToContactList")
            Log.d("ContactProjects", "Favorites에서 다시 받아온 데이터 : $getFavorites")
            getFavorites?.forEach { item ->
                val index = list.indexOfFirst { it.name == item.name }
                list[index].isLike = false
            }
            listAdapter.notifyDataSetChanged()
        }
        parentFragmentManager.setFragmentResultListener("FromDialogKey", this) { key, result ->
            val getDialog = result.getParcelableArrayList<AddMemberData>("FromDialog")
            Log.d("ContactProjects", "Fav   orites에서 다시 받아온 데이터 : $getDialog")
        }
        _binding = ContactListFragmentBinding.inflate(inflater, container, false)
        list.apply {
            add(
                UserDataModel(
                    R.drawable.chaewon, "010-4165-8974",
                    "채원"
                )
            )
            add(
                UserDataModel(
                    R.drawable.eunchae, "010-2717-2038",
                    "은채"
                )
            )
            add(
                UserDataModel(
                    R.drawable.kazuha, "010-1230-4518",
                    "카즈하"
                )
            )
            add(
                UserDataModel(
                    R.drawable.sakura, "010-2468-9510",
                    "사쿠라"
                )
            )
            add(
                UserDataModel(
                    R.drawable.yunjin, "010-7833-2232",
                    "허윤진"
                )
            )
            add(
                UserDataModel(
                    R.drawable.gaeul, "010-4467-2892",
                    "가을"
                )
            )
            add(
                UserDataModel(
                    R.drawable.iseo, "010-6541-6672",
                    "이서"
                )
            )
            add(
                UserDataModel(
                    R.drawable.ray, "010-6315-7632",
                    "레이"
                )
            )
            add(
                UserDataModel(
                    R.drawable.wonyoung, "010-7777-7777",
                    "장원영"
                )
            )
            add(
                UserDataModel(
                    R.drawable.yujin, "010-6547-1466",
                    "안유진"
                )
            )
            add(
                UserDataModel(
                    R.drawable.winter, "010-9788-8564",
                    "윈터"
                )
            )
            add(
                UserDataModel(
                    R.drawable.karina, "010-5436-1592",
                    "카리나"
                )
            )
            add(
                UserDataModel(
                    R.drawable.eunbi, "010-6154-9511",
                    "권은비"
                )
            )
            add(
                UserDataModel(
                    R.drawable.miyeon, "010-7750-0987",
                    "미연"
                )
            )
            add(
                UserDataModel(
                    R.drawable.ugi, "010-6543-7782",
                    "우기"
                )
            )
            add(
                UserDataModel(
                    R.drawable.yena, "010-2137-8224",
                    "최예나"
                )
            )
            add(
                UserDataModel(
                    R.drawable.yuna, "010-3357-6669",
                    "유나"
                )
            )

        }
        initView()

        binding.btnaddmember.setOnClickListener {
            val popUp = AddContactDialogFragment()
            popUp.show((activity as AppCompatActivity).supportFragmentManager, "popUp")
        }
        return binding.root
    }


    private fun initView() = with(binding) {
        contactListRe.layoutManager = LinearLayoutManager(context)
        contactListRe.adapter = listAdapter
        listAdapter.replace(list)
        contactListRe.setHasFixedSize(true)
        // ItemTouchHelper를 초기화하고 RecyclerView에 연결
        //val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallback(requireContext(), listAdapter))
        //itemTouchHelper.attachToRecyclerView(contactListRe)

    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


}
