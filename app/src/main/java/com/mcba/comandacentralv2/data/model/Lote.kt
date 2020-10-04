package com.mcba.comandacentralv2.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Lote(
    @SerializedName("idLote")
    val idLote: Int,
    @SerializedName("idProducto")
    val idProducto: Int,
    @SerializedName("descripcion")
    val descripcion: String,
    @SerializedName("fechaIngreso")
    val fechaIngreso: String,
    @SerializedName("cantidad")
    val cantidad: Double
) : Parcelable


@Entity(tableName = "loteEntity")
data class LoteEntity(
    @PrimaryKey(autoGenerate = false)
    val idLote: Int? = null,
    @ColumnInfo(name = "descripcion")
    val descripcion: String,
    @ColumnInfo(name = "fechaIngreso")
    val fechaIngreso: String,
    @ColumnInfo(name = "cantidad")
    val cantidad: Double
)