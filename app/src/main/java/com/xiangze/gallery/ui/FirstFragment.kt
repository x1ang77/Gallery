package com.xiangze.gallery.ui

import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.xiangze.gallery.adapters.GalleryAdapter
import com.xiangze.gallery.databinding.FragmentFirstBinding
import java.io.File

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private var images: MutableList<File> = mutableListOf()
    private lateinit var adapter: GalleryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(layoutInflater)
        Log.d("debugging","test")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val path = Environment.getExternalStorageDirectory().path
        getImages(path)
        Log.d("debug",path)

        images.map {
            Log.d("debugging",it.name)
        }
        adapter = GalleryAdapter(images)
        val layoutManager = GridLayoutManager(requireContext(),3)
        binding.rvImages.adapter = adapter
        binding.rvImages.layoutManager = layoutManager
    }

    private fun getImages(path:String){
        val root = File(path)
        val files = root.listFiles()?.toList() ?: listOf()
        for(file in files){
            if(Regex(".jpg|.jpeg|.png").containsMatchIn(file.name)){
                images.add(file)
            }
        }

        for(file in files){
            if(file.isDirectory){
                getImages(file.path)
            }
        }
    }
}