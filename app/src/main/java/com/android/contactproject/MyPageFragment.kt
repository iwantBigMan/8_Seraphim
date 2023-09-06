package com.android.contactproject

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.android.contactproject.databinding.FragmentMyPageBinding
import com.android.contactproject.databinding.MypageReviseDialogBinding
import com.bumptech.glide.Glide
import java.io.IOException

class MyPageFragment : Fragment() {
    private lateinit var binding: FragmentMyPageBinding
    private var selectedImageUri: Uri? = null


    private val REQUEST_CODE_PICK_IMAGE = 101

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.myImage.setImageResource(R.drawable.jisoo)
        binding.myName.text = "김지수"
        binding.myMobile.text = "010-9999-9999"
        binding.myEvent.text = "5분뒤 알림"

        binding.btnRevise.setOnClickListener {
            val v1 = layoutInflater.inflate(R.layout.mypage_revise_dialog, null)
            val dialog = AlertDialog.Builder(requireContext())
                .setTitle("내정보 수정")
                .setView(v1)
                .create()
            val setimage = v1.findViewById<ImageView>(R.id.dialog_image2)

            setimage.setOnClickListener {
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT) //  다른 앱에서 저장한 파일을 열 수 있는 Intent
                intent.addCategory(Intent.CATEGORY_OPENABLE) // Intent를 처리할 수 있는 앱 중에서 파일 열기 또는 데이터 열기 기능을 제공하는 앱을 선별,
                // 사용자가 파일 선택기를 통해 파일을 선택
                intent.type = "image/*" //intent.type = " 이건 선택할 파일의 타입 "
                startActivityForResult(
                    intent,
                    REQUEST_CODE_PICK_IMAGE
                ) // startActivityForResult를 호출,
                // 아래 onActivityResult 함수에서 사용자가 선택한 이미지를 처리
            }

            val btnAccept = v1.findViewById<Button>(R.id.dialog_acceptbtn)
            val btnCancel = v1.findViewById<Button>(R.id.dialog_cancelbtn)

            btnAccept.setOnClickListener {

                val editTextName = v1.findViewById<EditText>(R.id.dialog_name2)
                val editTextPhoneNumber = v1.findViewById<EditText>(R.id.dialog_phone2)
                val editTextEvent = v1.findViewById<EditText>(R.id.dialog_event2)
                val newName = editTextName.text.toString()
                val newPhoneNumber = editTextPhoneNumber.text.toString()
                val newEvent = editTextEvent.text.toString()

                if (selectedImageUri != null) {
                    Glide.with(this)
                        .load(selectedImageUri)
                        .into(binding.myImage)
                }



                binding.myName.text = newName
                binding.myMobile.text = newPhoneNumber
                binding.myEvent.text = newEvent

                dialog.dismiss()
            }
            btnCancel.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }
    }

    //위에 startActivityForResult로 시작된 액티비티가 종료 후 돌아올 때 호출
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            //이미지의 Uri를 가져온다.
            val uri = data?.data
            if (uri != null) {
                selectedImageUri = uri
                val v1 = layoutInflater.inflate(R.layout.mypage_revise_dialog, null)
                val setimage = v1.findViewById<ImageView>(R.id.dialog_image2)

            }
        }
    }


//    private fun getRealPathFromURI(uri: Uri): String? {
//        val projection = arrayOf(MediaStore.Images.Media.DATA)
//        val cursor = requireContext().contentResolver.query(uri, projection, null, null, null)
//        val columnIndex = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
//        cursor?.moveToFirst()
//        val filePath = cursor?.getString(columnIndex ?: -1)
//        cursor?.close()
//        return filePath
//    }

}