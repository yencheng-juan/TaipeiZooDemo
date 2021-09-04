package com.demo.taipeizoo.model

import android.text.TextUtils
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@JsonObject
data class PlantDataList(
    @SerializedName("results")
    var fullList: List<PlantData> = emptyList(),
)

@JsonObject
data class PlantData(
    @SerializedName("_id")
    var id: Int = -1,
    @SerializedName("F_Name_En")
    var nameEn: String = "",
    @SerializedName("F_Name_Latin")
    var nameLatin: String = "",
    @SerializedName("\uFEFFF_Name_Ch")
    var nameCh: String = "",
    @SerializedName("F_Location")
    var location: String = "",
    @SerializedName("F_Pic01_URL")
    var pic1Url: String = "",
    @SerializedName("F_Pic02_URL")
    var pic2Url: String = "",
    @SerializedName("F_Pic03_URL")
    var pic3Url: String = "",
    @SerializedName("F_AlsoKnown")
    var alsoKnown: String = "",
    @SerializedName("F_Brief")
    var brief: String = "",
    @SerializedName("F_Feature")
    var feature: String = "",
    @SerializedName("F_Family")
    var family: String = "",
    @SerializedName("F_Pic01_ALT")
    var pic1Alt: String = "",
    @SerializedName("F_Pic02_ALT")
    var pic2Alt: String = "",
    @SerializedName("F_Pic03_ALT")
    var pic3Alt: String = "",
    @SerializedName("F_Function＆Application")
    var functionAndApplication: String = "",
    @SerializedName("F_Genus")
    var genus: String = "",
    @SerializedName("F_Update")
    var update: String = ""
) : Serializable {

    fun isInZooArea(zooArea: ZooAreaData): Boolean =
        location.split("；").contains(zooArea.name)

    fun hasPic1() = !TextUtils.isEmpty(pic1Url)
    fun hasPic2() = !TextUtils.isEmpty(pic2Url)
    fun hasPic3() = !TextUtils.isEmpty(pic3Url)

    fun isSame(other: PlantData): Boolean {
        return id == other.id &&
                nameEn == other.nameEn &&
                nameLatin == other.nameLatin &&
                nameCh == other.nameCh &&
                location == other.location &&
                pic1Url == other.pic1Url &&
                pic2Url == other.pic2Url &&
                pic3Url == other.pic3Url &&
                alsoKnown == other.alsoKnown &&
                brief == other.brief &&
                feature == other.feature &&
                family == other.family &&
                pic1Alt == other.pic1Alt &&
                pic2Alt == other.pic2Alt &&
                pic3Alt == other.pic3Alt &&
                functionAndApplication == other.functionAndApplication &&
                genus == other.genus &&
                update == other.update
    }

    override fun toString(): String {
        return "PlantData(nameEn=$nameEn, " +
                "nameLatin=$nameLatin, " +
                "nameCh=$nameCh, " +
                "location=${location}, " +
                "pic1Url=$pic1Url, " +
                "pic2Url=$pic2Url, " +
                "pic3Url=$pic3Url, " +
                "alsoKnown=$alsoKnown, " +
                "brief=$brief, " +
                "feature=$feature, " +
                "family=$family, " +
                "pic1Alt=$pic1Alt, " +
                "pic2Alt=$pic2Alt, " +
                "pic3Alt=$pic3Alt, " +
                "functionAndApplication=$functionAndApplication, " +
                "genus=$genus, " +
                "update=$update"
    }
}