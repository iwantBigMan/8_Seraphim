package com.android.contactproject.contactlist

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class UserDataModel(val userImage: Int, val ph: String, val name: String, var isLike:Boolean
= false): Parcelable