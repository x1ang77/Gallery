package com.xiangze.gallery.ui

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.xiangze.gallery.MainActivity
import com.xiangze.gallery.R
import com.xiangze.gallery.adapter.ImageSliderAdapter
import com.xiangze.gallery.databinding.FragmentImageViewerBinding

class ImageViewerFragment : Fragment() {
    private lateinit var binding:FragmentImageViewerBinding
    private lateinit var adapter: ImageSliderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentImageViewerBinding.inflate(layoutInflater)
        return inflater.inflate(R.layout.fragment_image_viewer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: ImageViewerFragmentArgs by navArgs()

        val images = (requireActivity() as MainActivity).images
        val image = images[args.pos]

        adapter = ImageSliderAdapter(images)

        binding.vpImage.let { viewPager ->
            viewPager.adapter = adapter
            viewPager.getChildAt(args.pos)?.overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        }
    }
}