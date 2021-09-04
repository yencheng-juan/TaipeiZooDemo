package com.demo.taipeizoo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.taipeizoo.model.PlantData
import com.demo.taipeizoo.resource.PlantListResource
import com.demo.taipeizoo.resource.Resource

class ZooAreaDetailViewModel : ViewModel() {
    val plantList: MutableLiveData<Resource<List<PlantData>>> by lazy {
        PlantListResource().asLiveData()
    }
}