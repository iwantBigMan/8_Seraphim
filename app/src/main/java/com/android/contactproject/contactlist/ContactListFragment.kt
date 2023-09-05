package com.android.contactproject.contactlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.contactproject.R
import com.android.contactproject.databinding.ContactListFragmentBinding
import com.android.contactproject.databinding.ContactListItemBinding


class ContactListFragment : Fragment() {

     private var list = mutableListOf<UserDataModel>()

    private var _binding: ContactListFragmentBinding? = null
    private val binding get() = _binding!!

    private val listAdapter by lazy {
      ContactListFragmentAdapter()
    }




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ContactListFragmentBinding.inflate(inflater, container, false)
        list.apply { add(UserDataModel(R.drawable.ic_baseline_supervised_user_circle_24,"010-2717-2038", "남궁현",R.drawable.heart))
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
