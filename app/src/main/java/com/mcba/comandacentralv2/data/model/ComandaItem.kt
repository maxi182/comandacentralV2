package com.mcba.comandacentralv2.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ComandaItem(
    @SerializedName("itemId")
    val itemId: Int,
    @SerializedName("comandaId")
    val comandaId: Int,
    @SerializedName("comandaProductItem")
    val mProductItem: ComandaProductItem,
    @SerializedName("cant")
    val mCant: Double,
    @SerializedName("price")
    val mPrice: Double,
    @SerializedName("total")
    val mTotal: Double
) : Parcelable


@Entity(tableName = "comandaItemEntity")
data class ComandaItemEntity(
    @PrimaryKey(autoGenerate = true)
    val itemId: Int? = null,
    @ColumnInfo(name = "comanda_id")
    val comandaId: Int,
    @ColumnInfo(name = "product_item")
    val mProductItem: ComandaProductItemEntity,
    @ColumnInfo(name = "cant")
    val mCant: Double,
    @ColumnInfo(name = "price")
    val mPrice: Double,
    @ColumnInfo(name = "total")
    val mTotal: Double
)