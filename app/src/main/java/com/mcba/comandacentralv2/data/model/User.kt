package com.mcba.comandacentralv2.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var name: String?,
    var profile: String?
) : Parcelable {
    override fun toString(): String {
        return "$name"
    }
}

@Entity(tableName = "userEntity")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val userId: Int? = null,
    @ColumnInfo(name = "name")
    val mName: String = "",
    @ColumnInfo(name = "profile")
    val profile: String = ""
)
