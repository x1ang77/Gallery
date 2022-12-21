package com.xiangze.gallery.ui

import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.xiangze.gallery.R
import com.xiangze.gallery.adapters.ImageAdapter
import com.xiangze.gallery.databinding.FragmentFirstBinding
import java.io.File

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private val images: MutableList<File> = mutableListOf()
    private lateinit var adapter: ImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val path = Environment.getExternalStorageDirectory().path
        getImages(path)

        setupAdapter(images)
//        images.map { Log.d("debugging", images.size.toString()) }
    }

    private fun getImages(path: String) {
        val root = File(path)
        val files = root.listFiles()?.toList() ?: listOf()
        for (file in files) {
            if (Regex(".jpg|.png|.jpeg").containsMatchIn(file.name)) {
                images.add(file)
            }
        }

        for (file in files) {
            if (file.isDirectory) {
                getImages(file.path)
            }
        }
    }

    private fun setupAdapter(images: List<File>) {
        val layoutManager = GridLayoutManager(requireContext(), 3)

        adapter = ImageAdapter(images)

        binding.rvImages.layoutManager = layoutManager
        binding.rvImages.adapter = adapter

    }
}