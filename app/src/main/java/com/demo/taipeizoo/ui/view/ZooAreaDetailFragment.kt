package com.demo.taipeizoo.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.taipeizoo.R
import com.demo.taipeizoo.databinding.FragmentZooAreaDetailBinding
import com.demo.taipeizoo.model.PlantData
import com.demo.taipeizoo.model.ZooAreaData
import com.demo.taipeizoo.ui.view.PlantDetailFragment.Companion.EXTRA_PLANT_DATA
import com.demo.taipeizoo.ui.view.adapter.PlantListAdapter
import com.demo.taipeizoo.utils.launchBrowser
import com.demo.taipeizoo.viewmodel.ZooAreaDetailViewModel

class ZooAreaDetailFragment : Fragment() {

    companion object {
        const val EXTRA_ZOO_AREA_DATA = "extra_zoo_area_data"
    }

    private lateinit var rvPlant: RecyclerView
    private lateinit var plantListAdapter: PlantListAdapter

    private lateinit var viewModel: ZooAreaDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val zooAreaData = arguments?.get(EXTRA_ZOO_AREA_DATA) as ZooAreaData
        (activity as? AppCompatActivity)?.supportActionBar?.title = zooAreaData.name

        val binding: FragmentZooAreaDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_zoo_area_detail, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val zooAreaData = arguments?.get(EXTRA_ZOO_AREA_DATA) as ZooAreaData

        val navController = NavHostFragment.findNavController(this)

        plantListAdapter = PlantListAdapter().apply {
            setListener(object : PlantListAdapter.PlantListOnClickListener {
                override fun onPlantClick(plantData: PlantData) {
                    try {
                        navController.navigate(
                            R.id.action_zooAreaDetailFragment_to_plantDetailFragment,
                            Bundle().apply {
                                putSerializable(EXTRA_PLANT_DATA, plantData)
                            })
                    } catch (e: IllegalArgumentException) {
                        // IllegalArgumentException if click different items at the same time,
                        // so we just ignore the second click event
                    }
                }

                override fun onZooAreaLinkClick() {
                    activity?.let {
                        launchBrowser(it, zooAreaData.url)
                    }
                }
            })
            registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    if (!plantListAdapter.hasZooAreaData()) {
                        plantListAdapter.setZooAreaData(zooAreaData)
                        plantListAdapter.notifyItemInserted(0)
                    }
                }
            })
        }

        rvPlant = view.findViewById(R.id.rv_plant)
        rvPlant.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = plantListAdapter
        }

        viewModel = ViewModelProvider(this).get(ZooAreaDetailViewModel::class.java)
        viewModel.plantList.observe(viewLifecycleOwner, { resource ->
            when {
                resource.isSuccess() -> {
                    val plantListInZooArea = resource.data?.filter { platData ->
                        platData.isInZooArea(zooAreaData)
                    }
                    plantListAdapter.submitList(plantListInZooArea)
                }
                resource.isLoading() -> {
                }
                resource.isError() -> {
                    activity?.let { it ->
                        Toast.makeText(it, R.string.plant_list_error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

}