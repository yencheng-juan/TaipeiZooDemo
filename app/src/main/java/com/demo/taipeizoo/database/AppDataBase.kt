package com.demo.taipeizoo.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.demo.taipeizoo.ZooApplication
import com.demo.taipeizoo.database.dao.PlantDao
import com.demo.taipeizoo.database.dao.ZooAreaDao
import com.demo.taipeizoo.database.entity.PlantEntity
import com.demo.taipeizoo.database.entity.ZooAreaEntity


@Database(
    entities = [
        ZooAreaEntity::class,
        PlantEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class ZooDataBase : RoomDatabase(), ZooDBInterface {

    companion object {
        internal const val DB_NAME = "zoo.db"

        @Volatile
        private var sInstance: ZooDataBase? = null

        fun getDatabase(): ZooDataBase {

            sInstance?.let {
                return it
            }

            synchronized(this) {
                val instance = Room
                    .databaseBuilder(
                        ZooApplication.getApplicationContext(),
                        ZooDataBase::class.java,
                        DB_NAME
                    )
                    .addMigrations()
                    .fallbackToDestructiveMigration()
                    .build()
                sInstance = instance
                return instance
            }
        }
    }
}

open class AppDataBase : ZooDBInterface {
    override fun zooAreaDao(): ZooAreaDao {
        return sInstancePaintingDB.zooAreaDao()
    }

    override fun plantDao(): PlantDao {
        return sInstancePaintingDB.plantDao()
    }

    companion object {
        @Volatile
        private var sInstance: AppDataBase? = null

        @Volatile
        private lateinit var sInstancePaintingDB: ZooDataBase

        fun getDatabase(): AppDataBase {

            sInstance?.let {
                return it
            }

            synchronized(this) {
                sInstancePaintingDB = ZooDataBase.getDatabase()
                sInstance = AppDataBase()
                return sInstance as AppDataBase
            }
        }
    }
}