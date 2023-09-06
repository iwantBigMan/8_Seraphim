package com.android.contactproject.contactlist

import MyPageFragment
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.contactproject.ContactDetailFragment

import com.android.contactproject.R
import com.android.contactproject.databinding.ContactListFragmentBinding
import com.android.contactproject.databinding.ContactListItemBinding


class ContactListFragment : Fragment() {

     private var list = mutableListOf<UserDataModel>()

    private var _binding: ContactListFragmentBinding? = null
    private val binding get() = _binding!!
    val listArray = arrayListOf<UserDataModel>()
    private val listAdapter by lazy {
      ContactListFragmentAdapter(list).apply {
          itemClick = object:ContactListFragmentAdapter.ItemClick{
              override fun onClick(view: View, position: Int) {
                  val item = list[position]
                  val builder = AlertDialog.Builder(requireContext())
                  builder.setTitle("즐겨찾기")
                  builder.setMessage("즐겨찾기를 하시겠읍니까?")

                  val listener = object : DialogInterface.OnClickListener {
                      override fun onClick(dialog: DialogInterface?, which: Int) {
                          when (which) {
                              DialogInterface.BUTTON_POSITIVE -> {
                                 item.isLike = !item.isLike
                                  listArray.add(item)
                                  notifyDataSetChanged()
                                  val bundle = Bundle()
                                  bundle.putParcelableArrayList("ToFavorites", listArray)
                                  setFragmentResult("ToFavoritesKey",bundle)
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

              override fun onImageLongClick(view: View, position: Int) {
                  val bundle = Bundle()
                  val item = list[position]
                  bundle.putParcelable("UserData", item)
                  val transaction = requireActivity().supportFragmentManager.beginTransaction()
                  val contactDetailFragment = ContactDetailFragment()
                 contactDetailFragment.arguments = bundle
                  transaction.replace(R.id.main_layout, contactDetailFragment)
                  transaction.commit()
              }

          }

      }
    }




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ContactListFragmentBinding.inflate(inflater, container, false)
        list.apply {
            add(UserDataModel(R.drawable.ic_baseline_supervised_user_circle_24,"010-2717-2038",
                "남궁현"))
            add(UserDataModel(R.drawable.ic_baseline_supervised_user_circle_24,"010-2717-2038",
                "박준수"))
            add(UserDataModel(R.drawable.ic_baseline_supervised_user_circle_24,"010-2717-2038",
                "남윤희"))
            add(UserDataModel(R.drawable.ic_baseline_supervised_user_circle_24,"010-2717-2038",
                "이다민"))
            add(UserDataModel(R.drawable.ic_baseline_supervised_user_circle_24,"010-2717-2038",
                "박성수"))
          }
        initView()
        return binding.root
    }



    private fun initView() = with(binding) {
        contactListRe.layoutManager = LinearLayoutManager(context)
        contactListRe.adapter = listAdapter
        listAdapter.replace(list)
        contactListRe.setHasFixedSize(true)

    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }



}
