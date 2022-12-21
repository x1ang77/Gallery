package com.xiangze.gallery.fragments

import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.xiangze.gallery.MainActivity
import com.xiangze.gallery.R
import com.xiangze.gallery.adapters.FileAdapter
import com.xiangze.gallery.databinding.FragmentHomeBinding
import java.io.File
import java.nio.file.Files

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: FileAdapter
    private val images: MutableList<File> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val path = Environment.getExternalStorageDirectory().path
        getImages(path)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navArgs: HomeFragmentArgs by navArgs()

        (requireActivity() as MainActivity).images = images
        setupAdapter(images)

//        .setOnClickListener {
//            val action = HomeFragmentDirections.actionHomeToImageViewer(it.id)
//            NavHostFragment.findNavController(this).navigate(action)
//        }
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

    // droid4x
    // animotion??

    private fun setupAdapter(files: List<File>) {
        val layoutManager = GridLayoutManager(requireContext(), 3)
        adapter = FileAdapter(files) {
            val action = HomeFragmentDirections.actionHomeToImageViewer(it)
            NavHostFragment.findNavController(this).navigate(action)
        }
        binding.rvFiles.layoutManager = layoutManager
        binding.rvFiles.adapter = adapter
    }
}