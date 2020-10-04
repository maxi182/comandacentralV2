package com.mcba.comandacentralv2.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductType(
    @SerializedName("idLineaProducto")
    val idLProductType: Int,
    @SerializedName("descripcion")
    val descripcion: String,
    @SerializedName("proveedor")
    val proveedor: String,
    @SerializedName("lote")
    val loteList: List<Lote>
) : Parcelable


@Entity(tableName = "productTypeEntity")
data class ProductTypeEntity(
    @PrimaryKey(autoGenerate = false)
    val idLProductType: Int? = null,
    @ColumnInfo(name = "descripcion")
    val descripcion: String,
    @ColumnInfo(name = "loteList")
    val mLoteList: List<Lote>
)