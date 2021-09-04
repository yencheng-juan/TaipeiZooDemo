package com.demo.taipeizoo.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = PlantEntity.TABLE_NAME)
class PlantEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = COLUMN_ID)
    var id: Int,

    @ColumnInfo(name = COLUMN_NAME_EN)
    var nameEn: String = "",

    @ColumnInfo(name = COLUMN_NAME_LATIN)
    var nameLatin: String = "",

    @ColumnInfo(name = COLUMN_NAME_CH)
    var nameCh: String = "",

    @ColumnInfo(name = COLUMN_LOCATION)
    var location: String = "",

    @ColumnInfo(name = COLUMN_PIC_1_URL)
    var pic1Url: String = "",

    @ColumnInfo(name = COLUMN_PIC_2_URL)
    var pic2Url: String = "",

    @ColumnInfo(name = COLUMN_PIC_3_URL)
    var pic3Url: String = "",

    @ColumnInfo(name = COLUMN_ALSO_KNOWN)
    var alsoKnown: String = "",

    @ColumnInfo(name = COLUMN_BRIEF)
    var brief: String = "",

    @ColumnInfo(name = COLUMN_FEATURE)
    var feature: String = "",

    @ColumnInfo(name = COLUMN_FAMILY)
    var family: String = "",

    @ColumnInfo(name = COLUMN_PIC_1_ALT)
    var pic1Alt: String = "",

    @ColumnInfo(name = COLUMN_PIC_2_ALT)
    var pic2Alt: String = "",

    @ColumnInfo(name = COLUMN_PIC_3_ALT)
    var pic3Alt: String = "",

    @ColumnInfo(name = COLUMN_FUNCTION_AND_APPLICATION)
    var functionAndApplication: String = "",

    @ColumnInfo(name = COLUMN_GENUS)
    var genus: String = "",

    @ColumnInfo(name = COLUMN_UPDATE)
    var update: String = ""
) {
    companion object {
        const val TABLE_NAME = "plant_data"

        const val COLUMN_ID = "id"
        const val COLUMN_NAME_EN = "name_en"
        const val COLUMN_NAME_LATIN = "name_latin"
        const val COLUMN_NAME_CH = "name_ch"
        const val COLUMN_LOCATION = "location"
        const val COLUMN_PIC_1_URL = "pic1Url"
        const val COLUMN_PIC_2_URL = "pic2Url"
        const val COLUMN_PIC_3_URL = "pic3Url"
        const val COLUMN_ALSO_KNOWN = "alsoKnown"
        const val COLUMN_BRIEF = "brief"
        const val COLUMN_FEATURE = "feature"
        const val COLUMN_FAMILY = "family"
        const val COLUMN_PIC_1_ALT = "pic1Alt"
        const val COLUMN_PIC_2_ALT = "pic2Alt"
        const val COLUMN_PIC_3_ALT = "pic3Alt"
        const val COLUMN_FUNCTION_AND_APPLICATION = "functionAndApplication"
        const val COLUMN_GENUS = "genus"
        const val COLUMN_UPDATE = "update"
    }
}