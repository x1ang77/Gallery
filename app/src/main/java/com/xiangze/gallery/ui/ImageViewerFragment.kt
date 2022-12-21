package com.xiangze.gallery.ui

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import com.xiangze.gallery.MainActivity
import com.xiangze.gallery.R
import com.xiangze.gallery.adapters.ImageAdapter
import com.xiangze.gallery.adapters.ImageSliderAdapter
import com.xiangze.gallery.databinding.FragmentImageViewerBinding
import kotlinx.coroutines.delay

class ImageViewerFragment : Fragment() {
    private lateinit var binding: FragmentImageViewerBinding
    private lateinit var adapter: ImageSliderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageViewerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: ImageViewerFragmentArgs by navArgs()

        val images = (requireActivity() as MainActivity).images
        val image = images[args.pos]

        adapter = ImageSliderAdapter(images)

        binding.vpImages.let { viewPager ->
            viewPager.adapter = adapter
            viewPager.getChildAt(args.pos)?.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
    }

}