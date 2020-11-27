package com.alodok.testapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.alodok.testapp.R
import com.alodok.testapp.databinding.FragmentHomeBinding
import com.alodok.testapp.model.DataImage

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var items: MutableList<DataImage> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        setRecycleView()
        initData()

        return binding.root
    }

    private fun setRecycleView() {
        binding.rvListImage.setHasFixedSize(true)
        binding.rvListImage.layoutManager = LinearLayoutManager(context)
        binding.rvListImage.adapter = HomeAdapter(items)
    }

    private fun initData() {
        val nameImage = resources.getStringArray(R.array.name_image)
        val listImage = resources.obtainTypedArray(R.array.array_image)
        items.clear()
        for (i in nameImage.indices) {
            items.add(
                DataImage(
                    nameImage[i],
                    listImage.getResourceId(i, 0)
                )
            )
        }
        listImage.recycle()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}