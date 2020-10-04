package com.mcba.comandacentralv2.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductListData(
    @SerializedName("data")
    val data: List<ProductData>,
    @SerializedName("codigoError")
    val codError: String,
    @SerializedName("errorMessage")
    val errorMessage: String
) : Parcelable

@Entity(tableName = "productListDataEntity")
data class ProductListDataEntity(
    @PrimaryKey(autoGenerate = true)
    val listDataId: Int? = null,
    @ColumnInfo(name = "data")
    val mProductList: List<ProductData>
)