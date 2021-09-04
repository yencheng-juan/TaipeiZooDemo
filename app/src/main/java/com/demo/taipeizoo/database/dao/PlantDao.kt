package com.demo.taipeizoo.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.demo.taipeizoo.database.entity.PlantEntity

@Dao
interface PlantDao {

    @Transaction
    @Query("SELECT * FROM plant_data WHERE id = :id ")
    fun getPlantLiveData(id: String): LiveData<PlantEntity>

    @Transaction
    @Query("SELECT * FROM plant_data")
    fun getPlantListLiveData(): LiveData<List<PlantEntity>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlant(zooAreaList: List<PlantEntity>)

    @Query("DELETE FROM plant_data")
    fun deleteAll()
}