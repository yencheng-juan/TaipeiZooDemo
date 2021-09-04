package com.demo.taipeizoo.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demo.taipeizoo.databinding.ListItemPlantBinding
import com.demo.taipeizoo.databinding.ListItemZooAreaDetailBinding
import com.demo.taipeizoo.model.PlantData
import com.demo.taipeizoo.model.ZooAreaData

class PlantListAdapter : ListAdapter<PlantData?, RecyclerView.ViewHolder>(diffCallback) {

    companion object {
        const val ITEM_TYPE_ZOO_DETAIL = 0
        const val ITEM_TYPE_PLANT = 1

        val diffCallback = object : DiffUtil.ItemCallback<PlantData?>() {
            override fun areItemsTheSame(oldItem: PlantData, newItem: PlantData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PlantData, newItem: PlantData): Boolean {
                return oldItem.isSame(newItem)
            }
        }
    }

    interface PlantListOnClickListener {
        fun onPlantClick(plantData: PlantData)
        fun onZooAreaLinkClick()
    }

    private var listener: PlantListOnClickListener? = null
    private var zooAreaData: ZooAreaData? = null

    fun setListener(listener: PlantListOnClickListener?) {
        this.listener = listener
    }

    fun setZooAreaData(zooAreaData: ZooAreaData) {
        this.zooAreaData = zooAreaData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            ITEM_TYPE_ZOO_DETAIL -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: ListItemZooAreaDetailBinding =
                    ListItemZooAreaDetailBinding.inflate(layoutInflater, parent, false)
                binding.plantClickListener = listener
                return ZooAreaDetailHolder(binding)
            }
            ITEM_TYPE_PLANT -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: ListItemPlantBinding =
                    ListItemPlantBinding.inflate(layoutInflater, parent, false)
                binding.plantClickListener = listener
                return PlantHolder(binding)
            }
        }
        throw RuntimeException("Invalid view type $viewType in PlantListAdapter")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ZooAreaDetailHolder)?.apply {
            bind(zooAreaData)
        }
        (holder as? PlantHolder)?.apply {
            val plantData = getItem(position)
            bind(plantData)
        }
    }

    override fun getItemCount(): Int {
        if (hasZooAreaData()) {
            return super.getItemCount() + 1
        }
        return super.getItemCount()
    }

    override fun getItem(position: Int): PlantData? {
        if (hasZooAreaData()) {
            if (position == 0) {
                return null
            }
            return super.getItem(position - 1)
        }
        return super.getItem(position)
    }

    override fun getItemViewType(position: Int): Int = when {
        position == 0 && hasZooAreaData() -> ITEM_TYPE_ZOO_DETAIL
        else -> ITEM_TYPE_PLANT
    }

    fun hasZooAreaData() = zooAreaData != null

    class ZooAreaDetailHolder(private val binding: ListItemZooAreaDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(zooAreaData: ZooAreaData?) {
            binding.zooAreaData = zooAreaData
            binding.executePendingBindings()
        }
    }

    class PlantHolder(private val binding: ListItemPlantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(plantData: PlantData?) {
            binding.plantData = plantData
            binding.executePendingBindings()
        }
    }
}