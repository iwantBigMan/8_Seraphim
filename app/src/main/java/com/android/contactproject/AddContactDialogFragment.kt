package com.android.contactproject

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.android.contactproject.databinding.FragmentAddContactDialogBinding
import com.bumptech.glide.Glide


class AddContactDialogFragment : DialogFragment() {
    private val binding by lazy{ FragmentAddContactDialogBinding.inflate(layoutInflater)}
    lateinit var addMemberResult : ActivityResultLauncher<Intent>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.imageButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            addMemberResult.launch(intent)
        }
        addMemberResult = registerForActivityResult(ActivityResultContracts
            .StartActivityForResult()){
            if(it.resultCode == RESULT_OK && it.data !=null){
                val uri = it.data!!.data

                Glide.with(this)
                    .load(uri)
                    .into(binding.dialogImage)
            }
        }

        return binding.root
    }


}