package com.demo.taipeizoo.resource

import androidx.annotation.UiThread
import androidx.lifecycle.MutableLiveData
import com.demo.taipeizoo.database.ZooDbDataHelper
import com.demo.taipeizoo.database.entity.ZooAreaEntity
import com.demo.taipeizoo.model.ZooAreaData
import com.demo.taipeizoo.request.RequestManager
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.SingleOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ZooAreaListResource {

    private val result = MutableLiveData<Resource<List<ZooAreaData>>>()

    init {
        ZooDbDataHelper.getZooAreaListLiveData().observeForever {
            val zooAreaList = it.map { zooAreaEntity -> convertToZooAreaData(zooAreaEntity) }
            if (zooAreaList.isNotEmpty()) {
                result.value = Resource.success(zooAreaList)
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

    private fun retrieveDataFromOnline() {
        Single.create(SingleOnSubscribe<List<ZooAreaData>> { emitter ->
            val call = RequestManager.colorApi().getZooAreaDataList()
            val response = call.execute()

            if (response.isSuccessful) {
                val zooAreaList = response.body()?.result?.fullList
                if (zooAreaList != null) {
                    emitter.onSuccess(zooAreaList)
                    ZooDbDataHelper.saveZooAreaList(
                        zooAreaList.map { convertToZooAreaEntity(it) }
                    )
                } else {
                    emitter.onError(Throwable(response.message()))
                }
            } else {
                emitter.onError(Throwable(response.message()))
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<ZooAreaData>> {
                override fun onSuccess(t: List<ZooAreaData>) {
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

    private fun convertToZooAreaData(zooAreaEntity: ZooAreaEntity): ZooAreaData {
        return ZooAreaData(
            id = zooAreaEntity.id,
            name = zooAreaEntity.name,
            category = zooAreaEntity.category,
            info = zooAreaEntity.info,
            pictureUrl = zooAreaEntity.pictureUrl,
            url = zooAreaEntity.url,
            memo = zooAreaEntity.memo
        )
    }

    private fun convertToZooAreaEntity(zooAreaData: ZooAreaData): ZooAreaEntity {
        return ZooAreaEntity(
            id = zooAreaData.id,
            name = zooAreaData.name,
            category = zooAreaData.category,
            info = zooAreaData.info,
            pictureUrl = zooAreaData.pictureUrl,
            url = zooAreaData.url,
            memo = zooAreaData.memo
        )
    }

    fun asLiveData() = result
}