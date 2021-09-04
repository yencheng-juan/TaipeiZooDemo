package com.demo.taipeizoo.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.demo.taipeizoo.R
import com.demo.taipeizoo.databinding.FragmentPlantDetailBinding
import com.demo.taipeizoo.model.PlantData


class PlantDetailFragment : Fragment() {

    companion object {
        const val EXTRA_PLANT_DATA = "extra_plant_data"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val plantData = arguments?.get(EXTRA_PLANT_DATA) as PlantData

        (activity as? AppCompatActivity)?.supportActionBar?.title = plantData.nameCh

        val binding: FragmentPlantDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_plant_detail, container, false
        )
        binding.plantData = plantData
        return binding.root
    }

}