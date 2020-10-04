package com.mcba.comandacentralv2.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Client(
    var clientId: Int? = null,
    var name: String?
) : Parcelable {
    override fun toString(): String {
        return "$name"
    }
}

@Entity(tableName = "clientEntity")
data class ClientEntity(
    @PrimaryKey(autoGenerate = true)
    val clientId: Int? = null,
    @ColumnInfo(name = "name")
    val mName: String = ""
)
