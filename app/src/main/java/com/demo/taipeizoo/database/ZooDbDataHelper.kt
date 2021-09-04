package com.demo.taipeizoo.database

import androidx.lifecycle.LiveData
import com.demo.taipeizoo.database.entity.PlantEntity
import com.demo.taipeizoo.database.entity.ZooAreaEntity

object ZooDbDataHelper {

    fun getZooAreaListLiveData(): LiveData<List<ZooAreaEntity>> {
        return AppDataBase.getDatabase().zooAreaDao().getZooAreaListLiveData()
    }

    fun saveZooAreaList(zooAreaList: List<ZooAreaEntity>) {
        AppDataBase.getDatabase().zooAreaDao().deleteAll()
        AppDataBase.getDatabase().zooAreaDao().insertZooArea(zooAreaList)
    }

    fun getPlantListLiveData(): LiveData<List<PlantEntity>> {
        return AppDataBase.getDatabase().plantDao().getPlantListLiveData()
    }

    fun savePlantList(plantList: List<PlantEntity>) {
        AppDataBase.getDatabase().plantDao().deleteAll()
        AppDataBase.getDatabase().plantDao().insertPlant(plantList)
    }
}