package com.demo.taipeizoo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.taipeizoo.model.ZooAreaData
import com.demo.taipeizoo.resource.Resource
import com.demo.taipeizoo.resource.ZooAreaListResource

class ZooAreaListViewModel : ViewModel() {
    val zooAreaList: MutableLiveData<Resource<List<ZooAreaData>>> by lazy {
        ZooAreaListResource().asLiveData()
    }
}