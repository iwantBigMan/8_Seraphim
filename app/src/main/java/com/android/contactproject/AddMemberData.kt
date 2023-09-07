package com.android.contactproject

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AddMemberData(val profile: Uri, val name:String, val phone:String, val adress:String):
    Parcelable