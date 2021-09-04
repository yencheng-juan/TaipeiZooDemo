package com.demo.taipeizoo.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ZooAreaEntity.TABLE_NAME)
class ZooAreaEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = COLUMN_ID)
    var id: Int,

    @ColumnInfo(name = COLUMN_NAME)
    var name: String = "",

    @ColumnInfo(name = COLUMN_CATEGORY)
    var category: String = "",

    @ColumnInfo(name = COLUMN_INFO)
    var info: String = "",

    @ColumnInfo(name = COLUMN_PICTURE_URL)
    var pictureUrl: String = "",

    @ColumnInfo(name = COLUMN_URL)
    var url: String = "",

    @ColumnInfo(name = COLUMN_MEMO)
    var memo: String = ""
) {
    companion object {
        const val TABLE_NAME = "zoo_area_data"

        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_CATEGORY = "category"
        const val COLUMN_INFO = "info"
        const val COLUMN_PICTURE_URL = "picture_url"
        const val COLUMN_URL = "url"
        const val COLUMN_MEMO = "memo"
    }
}