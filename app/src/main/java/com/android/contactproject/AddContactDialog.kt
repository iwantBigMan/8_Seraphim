package com.android.contactproject

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.android.contactproject.databinding.ActivityAddContactDialogBinding

class AddContactDialog : AppCompatActivity() {
    private lateinit var binding: ActivityAddContactDialogBinding
    private var selectedUri : Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    class AddContact(context: Context, val activity: Activity) : Dialog(context) {
        private lateinit var binding: ActivityAddContactDialogBinding

        @SuppressLint("IntentReset")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityAddContactDialogBinding.inflate(layoutInflater)
            setContentView(binding.root)

            // imageView, EditText 선언
            val name = binding.dialogName
            val phone = binding.dialogPhone
            val address = binding.dialogAddress
            val event = binding.dialogEvent

            // 유효성 필드 완료 후 accept 버튼 활성화 시 필요
            var nameCheck = false
            var phoneCheck = false
            var addressCheck = false
            var eventCheck = false

            // 사진 추가 버튼 클릭 시
            binding.imageButton.setOnClickListener {
                Toast.makeText(context, "사진 추가를 위한 아이콘을 선택하셨습니다.", Toast.LENGTH_SHORT).show()
                // 갤러리 열기
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                intent.type = "image/*"
                activity.startActivityForResult(intent, GALLERY_CODE)
            }

            // name 텍스트 필드 유효성 검사
            name.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun afterTextChanged(s: Editable?) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val englishPattern = Regex("^[a-zA-Z]{4,}\$")
                    val koreanPattern = Regex("^[가-힣]{2,}\$")

                    if (englishPattern.matches(name.text) || koreanPattern.matches(name.text)) {
                        name.error = null
                        nameCheck = true
                    } else {
                        name.error = "이름은 4글자 이상(영어) or 2글자 이상(한글) 입력"
                        nameCheck = false
                    }
                }
            })

            // 번호 필드 유효성 검사
            phone.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun afterTextChanged(s: Editable?) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val phonePattern = Regex("^\\d{11}$")
                    if (phonePattern.matches(phone.text)) {
                        phone.error = null
                        phoneCheck = true
                    } else {
                        phone.error = "올바르지 않은 전화번호 형식"
                        phoneCheck = false
                    }
                }
            })

            // 이메일 필드 유효성 검사
            address.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun afterTextChanged(s: Editable?) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val emailPattern = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3}+)$")

                    if (emailPattern.matches(address.text)) {
                        address.error = null
                        addressCheck = true
                    } else {
                        address.error = ("올바르지 않는 이메일 형식")
                        addressCheck = false
                    }
                }

            })

            // event 필드 유효성 검사
            event.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun afterTextChanged(s: Editable?) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val eventPattern = Regex("^[A-Za-z0-9가-힣]*\$")
                    if (eventPattern.matches(event.text)) {
                        event.error = null
                        eventCheck = true
                    } else {
                        event.error = ("특수문자 사용 불가")
                        eventCheck = false
                    }
                    /*if(event.text.isEmpty()) {
                        event.error = ("정보를 입력해주세요.")
                        eventCheck = false
                    }*/
                }
            })

            // 취소 버튼 클릭 시
            binding.dialogCancelbtn.setOnClickListener {
                // dialog 닫기
                dismiss()
            }
            // 확인 버튼 클릭 시
            binding.dialogAcceptbtn.setOnClickListener {
                if (nameCheck && phoneCheck && addressCheck && eventCheck) {
                    // 입력한 데이터 전달
                    dismiss()
                } else {
                    Toast.makeText(context, "형식에 맞지 않은 정보가 존재합니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY_CODE && resultCode == Activity.RESULT_OK && data != null) {
            // 선택한 이미지 uri 데이터 저장
            selectedUri = data.data

            selectedUri?.let { uri ->
                binding.dialogImage.setImageURI(uri)
            }
        }
    }
    companion object {
        val GALLERY_CODE = 111
    }
}