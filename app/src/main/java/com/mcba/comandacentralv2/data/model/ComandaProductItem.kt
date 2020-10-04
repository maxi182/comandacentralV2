package com.mcba.comandacentralv2.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ComandaProductItem(
    @SerializedName("idItemProduct")
    val productItemId: Int,
    @SerializedName("idProduct")
    val productId: Int,
    @SerializedName("idLote")
    val loteId: Int,
    @SerializedName("descripcion")
    val descripcion: String = ""
) : Parcelable


@Entity(tableName = "productItemEntity")
data class ComandaProductItemEntity(
    @PrimaryKey(autoGenerate = true)
    val productItemId: Int? = null,
    @ColumnInfo(name = "id_Product")
    val productId: Int,
    @ColumnInfo(name = "id_Lote")
    val loteId: Int,
    @ColumnInfo(name = "item_descripcion")
    val descripcion: String = ""
)