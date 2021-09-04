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
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.taipeizoo.R
import com.demo.taipeizoo.databinding.FragmentZooAreaListBinding
import com.demo.taipeizoo.model.ZooAreaData
import com.demo.taipeizoo.ui.view.ZooAreaDetailFragment.Companion.EXTRA_ZOO_AREA_DATA
import com.demo.taipeizoo.ui.view.adapter.ZooAreaListAdapter
import com.demo.taipeizoo.viewmodel.ZooAreaListViewModel

class ZooAreaListFragment : Fragment() {

    private lateinit var rvZooArea: RecyclerView
    private lateinit var zooAreaListAdapter: ZooAreaListAdapter

    private lateinit var viewModel: ZooAreaListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.app_name)
        viewModel = ViewModelProvider(this).get(ZooAreaListViewModel::class.java)
        val binding: FragmentZooAreaListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_zoo_area_list, container, false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController(this)
        rvZooArea = view.findViewById(R.id.rv_zoo_area)
        zooAreaListAdapter = ZooAreaListAdapter().apply {
            setListener(object : ZooAreaListAdapter.ZooAreaListOnClickListener {
                override fun onClick(zooAreaData: ZooAreaData) {
                    try {
                        navController.navigate(
                            R.id.action_mainFragment_to_zooAreaDetailFragment,
                            Bundle().apply {
                                putSerializable(EXTRA_ZOO_AREA_DATA, zooAreaData)
                            }
                        )
                    } catch (e: IllegalArgumentException) {
                        // IllegalArgumentException if click different items at the same time,
                        // so we just ignore the second click event
                    }
                }
            })
        }
        rvZooArea.layoutManager = LinearLayoutManager(activity)
        rvZooArea.adapter = zooAreaListAdapter

        viewModel.zooAreaList.observe(viewLifecycleOwner, { resource ->
            when {
                resource.isSuccess() -> {
                    zooAreaListAdapter.submitList(resource.data)
                }
                resource.isLoading() -> {
                }
                resource.isError() -> {
                    activity?.let {
                        Toast.makeText(it, R.string.zoo_area_list_error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}