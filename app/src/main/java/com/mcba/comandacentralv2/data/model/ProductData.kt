package com.mcba.comandacentralv2.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ProductData(
    @SerializedName("idProducto")
    val productId: Int,
    @SerializedName("codigo")
    val codigo: Int,
    @SerializedName("descripcion")
    val descripcion: String,
    @SerializedName("lineaProducto")
    val loteList: List<ProductType>
) : Parcelable


@Entity(tableName = "productDataEntity")
data class ProductDataEntity(
    @PrimaryKey(autoGenerate = false)
    val productId: Int? = null,
    @ColumnInfo(name = "codigo")
    val codigo: Int,
    @ColumnInfo(name = "descripcion")
    val descripcion: String,
    @ColumnInfo(name = "lineaProductoList")
    val mLoteList: List<ProductType>
)