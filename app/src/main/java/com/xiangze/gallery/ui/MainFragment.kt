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
import com.xiangze.gallery.R
import com.xiangze.gallery.adapter.FileAdapter
import com.xiangze.gallery.databinding.FragmentMainBinding
import java.io.File

class MainFragment : Fragment() {
    private lateinit var binding:FragmentMainBinding
    private val images:MutableList<File> = mutableListOf()
    private lateinit var adapter:FileAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val path = Environment.getExternalStorageDirectory().path
        getImages(path)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).images=images
        setupAdapter(images)
    }


    private fun getImages(path:String){
        val root=File(path)
        val files=root.listFiles()?.toList() ?: listOf()
        for(file in files){
            if(Regex(".jpg|.png|.jpeg").containsMatchIn(file.name)){
                images.add(file)
            }
        }

        for(file in files){
            if(file.isDirectory){
                getImages(file.path)
            }
        }
    }
    private fun setupAdapter(images:List<File>){
        val layoutManager = GridLayoutManager(requireContext(),3)
        adapter = FileAdapter(images){
            val action = MainFragmentDirections.actionMainFragmentToImageViewerFragment(it)
            NavHostFragment.findNavController(this).navigate(action)
        }

        binding.rvImage.layoutManager = layoutManager
        binding.rvImage.adapter = adapter
    }

}