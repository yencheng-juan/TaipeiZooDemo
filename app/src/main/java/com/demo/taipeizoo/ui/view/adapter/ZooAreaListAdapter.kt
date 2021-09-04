package com.demo.taipeizoo.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demo.taipeizoo.databinding.ListItemZooAreaBinding
import com.demo.taipeizoo.model.ZooAreaData

class ZooAreaListAdapter : ListAdapter<ZooAreaData, RecyclerView.ViewHolder>(diffCallback) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<ZooAreaData>() {
            override fun areItemsTheSame(oldItem: ZooAreaData, newItem: ZooAreaData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ZooAreaData, newItem: ZooAreaData): Boolean {
                return oldItem.isSame(newItem)
            }
        }
    }

    interface ZooAreaListOnClickListener {
        fun onClick(zooAreaData: ZooAreaData)
    }

    private var listener: ZooAreaListOnClickListener? = null

    fun setListener(listener: ZooAreaListOnClickListener?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemZooAreaBinding =
            ListItemZooAreaBinding.inflate(layoutInflater, parent, false)
        binding.zooAreaClickListener = listener
        return ZooAreaHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val zooAreaData = getItem(position)
        (holder as? ZooAreaHolder)?.apply {
            bind(zooAreaData)
        }
    }

    class ZooAreaHolder(private val binding: ListItemZooAreaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(zooAreaData: ZooAreaData) {
            binding.zooAreaData = zooAreaData
            binding.executePendingBindings()
        }
    }
}