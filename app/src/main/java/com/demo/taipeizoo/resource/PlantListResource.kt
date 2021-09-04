package com.demo.taipeizoo.resource

import androidx.annotation.UiThread
import androidx.lifecycle.MutableLiveData
import com.demo.taipeizoo.database.ZooDbDataHelper
import com.demo.taipeizoo.database.entity.PlantEntity
import com.demo.taipeizoo.model.PlantData
import com.demo.taipeizoo.request.RequestManager
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.SingleOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PlantListResource {

    private val result = MutableLiveData<Resource<List<PlantData>>>()

    init {
        ZooDbDataHelper.getPlantListLiveData().observeForever {
            val plantList = it.map { plantEntity ->
                convertToPlantData(plantEntity)
            }
            if (plantList.isNotEmpty()) {
                result.value = Resource.success(plantList)
            }
        }
        fetchList()
    }

    @UiThread
    fun fetchList() {
        val resultValue = result.value
        resultValue?.let {
            if (it.isLoading()) {
                return
            }
        }

        result.value = (Resource(Status.LOADING, null, null))
        retrieveDataFromOnline()
    }

    @UiThread
    private fun retrieveDataFromOnline() {
        Single.create(SingleOnSubscribe<List<PlantData>> { emitter ->
            val call = RequestManager.colorApi().getPlantDataList()
            val response = call.execute()
            if (response.isSuccessful) {
                val plantList = response.body()?.result?.fullList
                if (plantList != null) {
                    ZooDbDataHelper.savePlantList(
                        plantList
                            .distinctBy { it.nameCh } // Remove duplicate
                            .map { convertToPlantEntity(it) }
                    )
                    emitter.onSuccess(plantList)
                } else {
                    emitter.onError(Throwable(response.message()))
                }
            } else {
                emitter.onError(Throwable(response.message()))
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
            object : SingleObserver<List<PlantData>> {
                override fun onSuccess(t: List<PlantData>) {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    if (result.value?.data.isNullOrEmpty()) {
                        result.value = Resource.error(e.message, null)
                    }
                }
            })
    }

    private fun convertToPlantData(plantEntity: PlantEntity): PlantData {
        return PlantData(
            id = plantEntity.id,
            nameEn = plantEntity.nameEn,
            nameLatin = plantEntity.nameLatin,
            nameCh = plantEntity.nameCh,
            location = plantEntity.location,
            pic1Url = plantEntity.pic1Url,
            pic2Url = plantEntity.pic2Url,
            pic3Url = plantEntity.pic3Url,
            alsoKnown = plantEntity.alsoKnown,
            brief = plantEntity.brief,
            feature = plantEntity.feature,
            family = plantEntity.family,
            pic1Alt = plantEntity.pic1Alt,
            pic2Alt = plantEntity.pic2Alt,
            pic3Alt = plantEntity.pic3Alt,
            functionAndApplication = plantEntity.functionAndApplication,
            genus = plantEntity.genus,
            update = plantEntity.update
        )
    }

    private fun convertToPlantEntity(plantData: PlantData): PlantEntity {
        return PlantEntity(
            id = plantData.id,
            nameEn = plantData.nameEn,
            nameLatin = plantData.nameLatin,
            nameCh = plantData.nameCh,
            location = plantData.location,
            pic1Url = plantData.pic1Url,
            pic2Url = plantData.pic2Url,
            pic3Url = plantData.pic3Url,
            alsoKnown = plantData.alsoKnown,
            brief = plantData.brief,
            feature = plantData.feature,
            family = plantData.family,
            pic1Alt = plantData.pic1Alt,
            pic2Alt = plantData.pic2Alt,
            pic3Alt = plantData.pic3Alt,
            functionAndApplication = plantData.functionAndApplication,
            genus = plantData.genus,
            update = plantData.update
        )
    }

    fun asLiveData() = result
}