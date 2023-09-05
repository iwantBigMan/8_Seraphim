package com.android.contactproject

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
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

        TabLayoutMediator(binding.tabLayout, binding.viewPager){tab, position ->
            tab.text = tabList[position]
        }.attach()

        binding.viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            var currentState = 0
            var currentPosition = 0

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if(currentState == ViewPager2.SCROLL_STATE_DRAGGING&&currentPosition == position){
                    if (currentPosition == 0) binding.viewPager.currentItem =2
                    else if(currentPosition ==2) binding.viewPager.currentItem =0
                }
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                currentPosition = position
                super.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                currentState = state
                super.onPageScrollStateChanged(state)
            }
        })

        // 플로팅 버튼 클릭시
        binding.btnaddmember.setOnClickListener {
            val click = AddContactDialog(this)
            click.show()
        }
    }

    class AddContactDialog(context: Context) : Dialog(context)  {

        private lateinit var binding : ActivityAddContactDialogBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            binding = ActivityAddContactDialogBinding.inflate(layoutInflater)
            setContentView(binding.root)

            val image = binding.dialogImage
            val name = binding.dialogName
            val phone = binding.dialogPhone
            val event = binding.dialogEvent

            // 사진 추가 버튼 클릭 시
            binding.imageButton.setOnClickListener {
                Toast.makeText(context, "사진 추가를 위한 아이콘을 선택하셨습니다.", Toast.LENGTH_SHORT).show()
                // 갤러리 열기
                val intent = Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                intent.type = "image/*"
                context.startActivity(intent)
                // 선택한 사진을 image에 등록하기
            }

            // 취소 버튼 클릭 시
            binding.dialogCancelbtn.setOnClickListener {
                // dialog 닫기
                dismiss()
            }

            // 확인 버튼 클릭 시
            binding.dialogAcceptbtn.setOnClickListener {
                // textview의 text 데이터 넘기기

                // dialog 닫기
                dismiss()
            }
        }
    }
}