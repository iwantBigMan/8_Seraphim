package com.android.contactproject.detailPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.UserData
import com.android.contactproject.R
import com.android.contactproject.contactlist.UserDataModel
import com.android.contactproject.databinding.ActivityContactDetailBinding
import com.android.contactproject.databinding.ActivityMainBinding

class ContactDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 데이터를 수신
        val userData = intent.getParcelableExtra<UserDataModel>("userData")

        if (userData != null) {
            // userData를 사용하여 필요한 작업을 수행
            binding.name2.text = "${userData.name}"
            binding.number.text = "${userData.ph}"
        }




    }
}