package com.demo.taipeizoo.database

import com.demo.taipeizoo.database.dao.PlantDao
import com.demo.taipeizoo.database.dao.ZooAreaDao

interface ZooDBInterface {
    fun zooAreaDao(): ZooAreaDao
    fun plantDao(): PlantDao
}