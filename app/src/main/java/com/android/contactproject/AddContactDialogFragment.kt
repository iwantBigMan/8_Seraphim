package com.android.contactproject

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.android.contactproject.databinding.FragmentAddContactDialogBinding
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

class AddContactDialogFragment : DialogFragment() {
    private val binding by lazy{ FragmentAddContactDialogBinding.inflate(layoutInflater)}
    lateinit var addMemberResult : ActivityResultLauncher<Intent>
    private lateinit var addMember : LinearLayout
    private var uri: Uri? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val lesserafimList = ArrayList<AddMemberData>()
        binding.apply {
            imageButton.setOnClickListener {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                addMemberResult.launch(intent)
            }
            dialogAcceptbtn.setOnClickListener {
                val name = dialogName.text.toString()
                val phone = dialogPhone.text.toString()
                val address = dialogAddress.text.toString()
                if (name.isNotBlank() && phone.isNotBlank() && address.isNotBlank()) {
                    if(uri != null){
                        val profile = uri ?: return@setOnClickListener
                        val lesserafim = AddMemberData(profile, name, phone, address)
                        lesserafimList.add(lesserafim)
                        val bundle = Bundle()
                        bundle.putParcelableArrayList("FromDialog", lesserafimList)
                        setFragmentResult("FromDialogKey", bundle)

                        dismiss()
                    }else{
                        Snackbar.make(requireView(), "프로필 사진을 선택 해주세요.", Snackbar.LENGTH_SHORT).apply {
                            anchorView = binding.dialogImage
                        }
                            .show()
                    }
                } else {
                    Snackbar.make(requireView(), "정보를 모두 입력하세요.", Snackbar.LENGTH_SHORT).apply {
                        anchorView = binding.dialogImage
                    }.show()
                }
            }
            dialogCancelbtn.setOnClickListener {
                dismiss()
            }
        }

        addMemberResult = registerForActivityResult(ActivityResultContracts
            .StartActivityForResult()){
            if(it.resultCode == RESULT_OK && it.data !=null){
                uri = it.data!!.data

                Glide.with(this)
                    .load(uri)
                    .into(binding.dialogImage)
            }
        }
        return binding.root
    }
}