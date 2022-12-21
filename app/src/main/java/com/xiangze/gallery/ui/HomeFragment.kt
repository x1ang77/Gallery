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
import com.xiangze.gallery.MainActivity
import com.xiangze.gallery.adapters.FileAdapter
import com.xiangze.gallery.databinding.FragmentHomeBinding
import java.io.File

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
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
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).images = images
        images.map {
            Log.d("debugging", it.name)
        }
        setupAdapter(images)
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
        adapter = FileAdapter(images) {
            val action = HomeFragmentDirections.actionHomeFragmentToImageViewerFragment(it)
            NavHostFragment.findNavController(this).navigate(action)
        }
        binding.rvImage.layoutManager = layoutManager
        binding.rvImage.adapter = adapter
    }
}

//