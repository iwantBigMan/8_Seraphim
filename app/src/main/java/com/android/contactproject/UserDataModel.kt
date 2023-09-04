package com.android.contactproject

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserDataModel(val ph: Int, val name: String) : Parcelable