package com.mcba.comandacentralv2.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Comanda(
    @SerializedName("idComanda")
    val comandaId: String = "",
    @SerializedName("date")
    val date: String = "",
    @SerializedName("isPrinted")
    val isPrinted: Boolean,
    @SerializedName("isSent")
    val isSent: Boolean,
    @SerializedName("idClient")
    val clientId: Int,
    @SerializedName("client_name")
    val clientName: String,
    @SerializedName("timestamp")
    val timestamp: Long,
    @SerializedName("cant_bultos")
    val cantBultos: Double,
    @SerializedName("total")
    val total: Double,
    @SerializedName("comandaItemList")
    val mComandaItemList: List<ComandaItem>
) : Parcelable


@Entity(tableName = "comandaEntity")
data class ComandaEntity(
    @PrimaryKey(autoGenerate = true)
    val comandaId: Int? = null,
    @ColumnInfo(name = "date")
    val date: String = "",
    @ColumnInfo(name = "isPrinted")
    val isPrinted: Boolean,
    @ColumnInfo(name = "isSent")
    val isSent: Boolean,
    @ColumnInfo(name = "idClient")
    val clientId: Int,
    @ColumnInfo(name = "client_name")
    val clientName: String,
    @ColumnInfo(name = "timestamp")
    val timestamp: Long,
    @ColumnInfo(name = "cant_bultos")
    val cantBultos: Double,
    @ColumnInfo(name = "total")
    val mTotal: Double,
    @ColumnInfo(name = "comandaItemList")
    val mComandaItemList: List<ComandaItemEntity>
)