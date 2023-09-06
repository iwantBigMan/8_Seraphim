package com.android.contactproject

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.android.contactproject.contactlist.UserDataModel
import com.android.contactproject.databinding.FragmentContactDetailBinding
import com.android.contactproject.databinding.FragmentMyPageBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ContactDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ContactDetailFragment : Fragment() {
    private lateinit var binding : FragmentContactDetailBinding
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


//        // 이벤트가 trur면 보이고 false면 안보이게
//        val Event = intent?.getbooleanExtera()
//        //이벤트 레이아웃 보임
//        if (Event == true){
//            val event = binding.Eventlayout
//            event.visibility = View.VISIBLE
//        }

//        //숨김
//        else if (Event == false){
//            val event = binding.Eventlayout
//        event.visibility = View.GONE
//        }

//        //받아온 데이터를 UI에 표시
//        binding.character.setImageResource()
//        binding.name2.text()
//        binding.number.text()
//        binding.Event.text()
//        binding.wort.text()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val userData = arguments?.getParcelable<UserDataModel>("UserData")

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_detail, container, false)
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ContactDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ContactDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}