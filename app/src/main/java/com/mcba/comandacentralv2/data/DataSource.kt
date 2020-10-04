package com.mcba.comandacentralv2.data

import com.mcba.comandacentralv2.AppDatabase
import com.mcba.comandacentralv2.api.RestClient
import com.mcba.comandacentralv2.api.UseCaseResult
import com.mcba.comandacentralv2.data.model.*


/**
 * Created by Gastón Saillén on 03 July 2020
 */
class DataSource(private val appDatabase: AppDatabase) {

    suspend fun getTragoByName(nombreTrago: String): UseCaseResult<List<Drink>> {
        return UseCaseResult.Success(
            RestClient.getApiService().getTragoByName(nombreTrago).drinksList
        )
    }

    suspend fun getLogin(name: String, password: String): User {
        return RestClient.getApiService().getUser(name)
    }

    suspend fun saveLogin(user: UserEntity) {
        return appDatabase.UserDao().insertUser(user)
    }

    suspend fun getProducts(): UseCaseResult<ProductListDataEntity> {
        return UseCaseResult.Success(
            appDatabase.ProductListDao().getProducts()
        )
    }

    suspend fun saveProducts(products: ProductDataEntity): Long {
        return appDatabase.ProductDao().insertProduct(products)
    }

    suspend fun saveListProducts(products: ProductListDataEntity): Long {
        return appDatabase.ProductListDao().insertProduct(products)
    }


    suspend fun getUser(): UseCaseResult<UserEntity> {
        return UseCaseResult.Success(
            appDatabase.UserDao().getUser()
        )
    }

    suspend fun getComandaItems(): UseCaseResult<List<ComandaItemEntity>> {
        return UseCaseResult.Success(
            appDatabase.ComandaItemDao().getAllComandaItems()
        )
    }

    suspend fun insertComandaItem(item: ComandaItemEntity): Long {
        return appDatabase.ComandaItemDao().insertComandaItem(item)
    }

    suspend fun getComandas(): UseCaseResult<List<ComandaEntity>> {
        return UseCaseResult.Success(
            appDatabase.ComandaDao().getAllComandas()
        )
    }

    suspend fun insertComanda(item: ComandaEntity): Long {
        return appDatabase.ComandaDao().insertComanda(item)
    }
//
//    suspend fun getTragosFavoritos(): UseCaseResult<List<DrinkEntity>> {
//        return UseCaseResult.Success(appDatabase.tragoDao().getAllFavoriteDrinks())
//    }
//
//    suspend fun deleteDrink(drink: DrinkEntity) {
//        appDatabase.tragoDao().deleteDrink(drink)
//    }
}