package com.android.contactproject

import android.app.Activity.NOTIFICATION_SERVICE
import android.app.Activity.RESULT_OK
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import androidx.fragment.app.DialogFragment
import com.android.contactproject.databinding.FragmentAddContactDialogBinding
import com.bumptech.glide.Glide


class AddContactDialogFragment : DialogFragment() {
    private val binding by lazy{ FragmentAddContactDialogBinding.inflate(layoutInflater)}
    lateinit var addMemberResult : ActivityResultLauncher<Intent>
    lateinit var setting : SharedPreferences


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

        // imageView, EditText 선언
        val name = binding.dialogName
        val phone = binding.dialogPhone
        val address = binding.dialogAddress
        val btnOff = binding.offBtn
        val btn5m = binding.btn5m
        val btn10m = binding.btn10m
        val btn30m = binding.btn30m


        // 유효성 필드 완료 후 accept 버튼 활성화 시 필요
        var nameCheck = false
        var phoneCheck = false
        var addressCheck = false


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
        var selectedBtn: Button? = null

        btnOff.setOnClickListener {
            // 선택한 버튼이 선택된 버튼과 동일하지 않은 경우에 실행 (selectedBtn = null ,, btnOf = binding.btnOff)
            if (selectedBtn != btnOff) {
                // 선택한 버튼이 이미 선택된 버튼일 경우, 선택 해제 상태
                selectedBtn?.setBackgroundResource(R.drawable.normalbtn)
                // 선택된 버튼의 배경색상을 선택 상태로 변경
                btnOff.setBackgroundResource(R.drawable.changebtn)
                // 선택한 버튼을 선택된 버튼으로 업데이트
                selectedBtn = btnOff
            }
        }

        btn5m.setOnClickListener {
            if (selectedBtn != btn5m) {
                selectedBtn?.setBackgroundResource(R.drawable.normalbtn)
                btn5m.setBackgroundResource(R.drawable.changebtn)
                selectedBtn = btn5m
            }
        }

        btn10m.setOnClickListener {
            if (selectedBtn != btn10m) {
                selectedBtn?.setBackgroundResource(R.drawable.normalbtn)
                btn10m.setBackgroundResource(R.drawable.changebtn)
                selectedBtn = btn10m
            }
        }

        btn30m.setOnClickListener {
            if (selectedBtn != btn30m) {
                selectedBtn?.setBackgroundResource(R.drawable.normalbtn)
                btn30m.setBackgroundResource(R.drawable.changebtn)
                selectedBtn = btn30m
            }
        }
        // 취소 버튼 클릭 시
        binding.dialogCancelbtn.setOnClickListener {
            // dialog 닫기
            dismiss()
        }
        // 확인 버튼 클릭 시
        binding.dialogAcceptbtn.setOnClickListener {
            if (nameCheck && phoneCheck && addressCheck && selectedBtn != null) {
                ReservationNotification(requireContext())
                // 입력한 데이터 전달
                dismiss()
            } else {
                Toast.makeText(context, "형식에 맞지 않은 정보가 존재합니다.", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

    private fun ReservationNotification(context : Context){
        val manager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val builder : NotificationCompat.Builder

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channelID = "8_Sheraphim channel"
            val channelName = "Contact Channel"
            val channel = NotificationChannel(
                channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "8_Sheraphim Description"
                setShowBadge(true)

                val uri : Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                setSound(uri, audioAttributes)
                enableVibration(true)
            }
            // 채널 등록
            manager.createNotificationChannel(channel)

            // 채널로 builder 생성
            builder = NotificationCompat.Builder(context, channelID)
        }
        else {
            builder =  NotificationCompat.Builder(context)
        }

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.bell)
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        builder.run {
            setSmallIcon(R.drawable.bell)
            setWhen(System.currentTimeMillis())
            setContentText("연락처 알림 !")
            setContentText("who 님이 기다리고 있어요...\nwho 님에게 연락하실 시간입니다 !")
            addAction(R.drawable.bell, "Action", pendingIntent)
        }
        manager.notify(11, builder.build())
    }
}