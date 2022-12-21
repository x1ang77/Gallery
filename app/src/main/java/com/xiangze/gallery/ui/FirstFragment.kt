package com.xiangze.gallery.ui

import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.xiangze.gallery.MainActivity
import com.xiangze.gallery.R
import com.xiangze.gallery.adapters.FileAdapter
import com.xiangze.gallery.databinding.FragmentFirstBinding
import java.io.File
import java.nio.file.Path

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private lateinit var adapter: FileAdapter
    private val images: MutableList<File> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val path = Environment.getExternalStorageDirectory().path
        getImages(path)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()

        (requireActivity() as MainActivity).images = images
        images.map { Log.d("debugging", it.name) }
        Log.d("debugging", images.size.toString())
    }

    private fun getImages(path: String) {
        val root = File(path)
        val files = root.listFiles()?.toList() ?: listOf()

        for (file in files) {
            if (Regex(".jpg|.png|.jpeg|.gif").containsMatchIn(file.name))
                images.add(file)
        }

        for (file in files) {
            if (file.isDirectory)
                getImages(file.path)
        }
    }

    private fun setupAdapter() {
        val layoutManager = GridLayoutManager(requireContext(), 3)
        adapter = FileAdapter(images) {
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(it)
            NavHostFragment.findNavController(this).navigate(action)
        }

        binding.rvImage.adapter = adapter
        binding.rvImage.layoutManager = layoutManager
    }

}