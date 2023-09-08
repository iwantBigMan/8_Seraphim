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
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.android.contactproject.databinding.ActivityAddContactDialogBinding
import com.android.contactproject.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val tabList = listOf("ContactList", "Favorites", "MyPage")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabList[position]
        }.attach()

//        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            var currentState = 0
//            var currentPosition = 0
//
//            override fun onPageScrolled(
//                position: Int,
//                positionOffset: Float,
//                positionOffsetPixels: Int
//            ) {
//                if (currentState == ViewPager2.SCROLL_STATE_DRAGGING && currentPosition == position) {
//                    if (currentPosition == 0) binding.viewPager.currentItem = 2
//                    else if (currentPosition == 2) binding.viewPager.currentItem = 0
//                }
//                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
//            }
//
//            override fun onPageSelected(position: Int) {
//                currentPosition = position
//                super.onPageSelected(position)
//            }
//
//            override fun onPageScrollStateChanged(state: Int) {
//                currentState = state
//                super.onPageScrollStateChanged(state)
//            }
//        })

        // 플로팅 버튼 클릭시
//        binding.btnaddmember.setOnClickListener {
//
//            selectedUri = Uri.EMPTY
//            val click = AddContactDialog(this@MainActivity, this@MainActivity, selectedUri)
//            click.show()
//        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        val basicImage = findViewById<ImageView>(R.id.dialog_image)
//
//        if (requestCode == GALLERY_CODE && resultCode == Activity.RESULT_OK && data != null) {
//            // 선택한 이미지 uri 데이터 저장
//            selectedUri = data.data!!
//        }
//    }

//    class AddContactDialog(context: Context, val activity: Activity, private val imageUri: Uri) :
//        Dialog(context) {
//        private lateinit var binding: ActivityAddContactDialogBinding
//
//        @SuppressLint("IntentReset")
//        override fun onCreate(savedInstanceState: Bundle?) {
//            super.onCreate(savedInstanceState)
//            binding = ActivityAddContactDialogBinding.inflate(layoutInflater)
//            setContentView(binding.root)
//
//
//            // imageView, EditText 선언
//            val name = binding.dialogName
//            val phone = binding.dialogPhone
//            val address = binding.dialogAddress
//            val btnOff = binding.offBtn
//            val btn5m = binding.btn5m
//            val btn10m = binding.btn10m
//            val btn30m = binding.btn30m
//
//
//            // 유효성 필드 완료 후 accept 버튼 활성화 시 필요
//            var nameCheck = false
//            var phoneCheck = false
//            var addressCheck = false
//            var eventCheck = false
//
//            // 사진 추가 버튼 클릭 시
//            binding.imageButton.setOnClickListener {
//                Toast.makeText(context, "사진 추가를 위한 아이콘을 선택하셨습니다.", Toast.LENGTH_SHORT).show()
//                // 갤러리 열기
//                val intent =
//                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//                intent.type = "image/*"
//                activity.startActivityForResult(intent, GALLERY_CODE)
//            }
//
//            // name 텍스트 필드 유효성 검사
//            name.addTextChangedListener(object : TextWatcher {
//                override fun beforeTextChanged(
//                    s: CharSequence?,
//                    start: Int,
//                    count: Int,
//                    after: Int
//                ) {
//                }
//
//                override fun afterTextChanged(s: Editable?) {}
//
//                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                    val englishPattern = Regex("^[a-zA-Z]{4,}\$")
//                    val koreanPattern = Regex("^[가-힣]{2,}\$")
//
//                    if (englishPattern.matches(name.text) || koreanPattern.matches(name.text)) {
//                        name.error = null
//                        nameCheck = true
//                    } else {
//                        name.error = "이름은 4글자 이상(영어) or 2글자 이상(한글) 입력"
//                        nameCheck = false
//                    }
//                }
//            })
//
//            // 번호 필드 유효성 검사
//            phone.addTextChangedListener(object : TextWatcher {
//                override fun beforeTextChanged(
//                    s: CharSequence?,
//                    start: Int,
//                    count: Int,
//                    after: Int
//                ) {
//                }
//
//                override fun afterTextChanged(s: Editable?) {}
//
//                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                    val phonePattern = Regex("^\\d{11}$")
//                    if (phonePattern.matches(phone.text)) {
//                        phone.error = null
//                        phoneCheck = true
//                    } else {
//                        phone.error = "올바르지 않은 전화번호 형식"
//                        phoneCheck = false
//                    }
//                }
//            })
//
//            // 이메일 필드 유효성 검사
//            address.addTextChangedListener(object : TextWatcher {
//                override fun beforeTextChanged(
//                    s: CharSequence?,
//                    start: Int,
//                    count: Int,
//                    after: Int
//                ) {
//                }
//
//                override fun afterTextChanged(s: Editable?) {}
//
//                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                    val emailPattern = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3}+)$")
//
//                    if (emailPattern.matches(address.text)) {
//                        address.error = null
//                        addressCheck = true
//                    } else {
//                        address.error = ("올바르지 않는 이메일 형식")
//                        addressCheck = false
//                    }
//                }
//
//            })
//
//            // event 필드 유효성 검사
//            var selectedBtn: Button? = null
//
//            btnOff.setOnClickListener {
//                // 선택한 버튼이 선택된 버튼과 동일하지 않은 경우에 실행 (selectedBtn = null ,, btnOf = binding.btnOff)
//                if(selectedBtn != btnOff) {
//                    // 선택한 버튼이 이미 선택된 버튼일 경우, 선택 해제 상태
//                    selectedBtn?.setBackgroundResource(R.drawable.normalbtn)
//                    // 선택된 버튼의 배경색상을 선택 상태로 변경
//                    btnOff.setBackgroundResource(R.drawable.changebtn)
//                    // 선택한 버튼을 선택된 버튼으로 업데이트
//                    selectedBtn = btnOff
//                }
//            }
//
//            btn5m.setOnClickListener {
//                if(selectedBtn != btn5m) {
//                    selectedBtn?.setBackgroundResource(R.drawable.normalbtn)
//                    btn5m.setBackgroundResource(R.drawable.changebtn)
//                    selectedBtn = btn5m
//                }
//            }
//
//            btn10m.setOnClickListener {
//                if(selectedBtn != btn10m){
//                    selectedBtn?.setBackgroundResource(R.drawable.normalbtn)
//                    btn10m.setBackgroundResource(R.drawable.changebtn)
//                    selectedBtn = btn10m
//                }
//            }
//
//            btn30m.setOnClickListener {
//                if(selectedBtn != btn30m) {
//                    selectedBtn?.setBackgroundResource(R.drawable.normalbtn)
//                    btn30m.setBackgroundResource(R.drawable.changebtn)
//                    selectedBtn = btn30m
//                }
//            }
//
//
//            // 취소 버튼 클릭 시
//            binding.dialogCancelbtn.setOnClickListener {
//                // dialog 닫기
//                dismiss()
//            }
//            // 확인 버튼 클릭 시
//            binding.dialogAcceptbtn.setOnClickListener {
//                if (nameCheck && phoneCheck && addressCheck && eventCheck) {
//                    // 입력한 데이터 전달
//                    dismiss()
//                } else {
//                    Toast.makeText(context, "형식에 맞지 않은 정보가 존재합니다.", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//
//        }
//    }
//
//    companion object {
//        val GALLERY_CODE = 111
//    }
}