package com.android.contactproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.contactproject.databinding.FragmentContactListBinding

class ContactListFragment : Fragment() {
    companion object {
        fun newInstance() = ContactListFragment()
    }

    private var _binding: FragmentContactListBinding? = null
    private val binding get() = _binding!!

    private val listAdapter by lazy {
      ContactListFragmentAdapter()
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = with(binding) {
        contactListRe.adapter = listAdapter

    }

    fun setDodoContent(userDataModel: UserDataModel?) {
        if (userDataModel != null) {
            listAdapter.addItem(userDataModel)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}
