package com.mcba.comandacentralv2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mcba.comandacentralv2.Utils.RoomConverters
import com.mcba.comandacentralv2.data.model.*
import com.mcba.comandacentralv2.domain.dao.*

/**
 * Created by Gastón Saillén on 07 July 2020
 */


@Database(
    entities = [ComandaItemEntity::class, ComandaProductItemEntity::class, ComandaEntity::class, UserEntity::class, ProductDataEntity::class, ProductListDataEntity::class],
    version = 1
)
@TypeConverters(RoomConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun ComandaDao(): ComandaDao
    abstract fun ComandaItemDao(): ComandaItemDao
    abstract fun UserDao(): UserDao
    abstract fun ProductDao(): ProductDao
    abstract fun ProductListDao(): ProductListDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "tabla_productos"
            ).build()
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}