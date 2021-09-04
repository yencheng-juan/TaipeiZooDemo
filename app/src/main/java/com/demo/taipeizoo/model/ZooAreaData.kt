package com.demo.taipeizoo.model

import android.text.TextUtils
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@JsonObject
data class ZooAreaDataList(
    @SerializedName("results")
    var fullList: List<ZooAreaData> = emptyList(),
)

@JsonObject
data class ZooAreaData(
    @SerializedName("_id")
    var id: Int = -1,
    @SerializedName("E_Name")
    var name: String = "",
    @SerializedName("E_Category")
    var category: String = "",
    @SerializedName("E_Info")
    var info: String = "",
    @SerializedName("E_Pic_URL")
    var pictureUrl: String = "",
    @SerializedName("E_URL")
    var url: String = "",
    @SerializedName("E_Memo")
    var memo: String = ""
) : Serializable {

    fun hasUrl() = !TextUtils.isEmpty(url)
    fun hasMemo() = !TextUtils.isEmpty(memo)

    fun isSame(other: ZooAreaData): Boolean {
        return id == other.id &&
                name == other.name &&
                category == other.category &&
                info == other.info &&
                pictureUrl == other.pictureUrl &&
                url == other.url &&
                memo == other.memo
    }

    override fun toString(): String {
        return "ZooAreaData(id=$id, " +
                "name=$name, " +
                "category=$category, " +
                "info=${info.substring(0, 20)}, " +
                "pictureUrl=$pictureUrl, " +
                "url=$url, " +
                "memo=$memo"
    }
}