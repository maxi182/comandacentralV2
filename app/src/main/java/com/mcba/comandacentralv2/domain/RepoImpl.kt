package com.mcba.comandacentralv2.domain

import com.mcba.comandacentralv2.data.DataSource
import com.mcba.comandacentralv2.data.model.Drink
import com.mcba.comandacentralv2.data.model.DrinkEntity


/**
 * Created by Gastón Saillén on 03 July 2020
 */
class RepoImpl(private val dataSource: DataSource): Repo {

//    override suspend fun getProductList(nombreTrago:String): Resource<List<Drink>> {
//        return dataSource.getTragoByName(nombreTrago)
//    }
//
//    override suspend fun getTragosFavoritos(): Resource<List<DrinkEntity>> {
//        return dataSource.getTragosFavoritos()
//    }
//
//    override suspend fun insertTrago(trago: DrinkEntity) {
//        dataSource.insertTragoIntoRoom(trago)
//    }
//
//    override suspend fun deleteDrink(drink: DrinkEntity) {
//        dataSource.deleteDrink(drink)
//    }
}