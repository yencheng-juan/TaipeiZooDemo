package com.demo.taipeizoo.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.demo.taipeizoo.database.entity.ZooAreaEntity

@Dao
interface ZooAreaDao {

    @Transaction
    @Query("SELECT * FROM zoo_area_data WHERE id = :id ")
    fun getZooAreaLiveData(id: String): LiveData<ZooAreaEntity>

    @Transaction
    @Query("SELECT * FROM zoo_area_data")
    fun getZooAreaListLiveData(): LiveData<List<ZooAreaEntity>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertZooArea(zooAreaList: List<ZooAreaEntity>)

    @Query("DELETE FROM zoo_area_data")
    fun deleteAll()
}