package com.xiangze.gallery.ui

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.xiangze.gallery.MainActivity
import com.xiangze.gallery.R
import com.xiangze.gallery.adapters.FileAdapter
import com.xiangze.gallery.adapters.ImageSliderAdapter
import com.xiangze.gallery.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private lateinit var adapter: ImageSliderAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: SecondFragmentArgs by navArgs()
        val images = (requireActivity() as MainActivity).images
        val image = images[args.pos]

        Log.d("debugging",images.size.toString())

        binding.btnBack.setOnClickListener {
            val bundle = Bundle()
            setFragmentResult("from_image_gallery",bundle)
            NavHostFragment.findNavController(this).popBackStack()
        }
        adapter = ImageSliderAdapter(images)
        binding.ivImage.let { viewPager ->
            viewPager.adapter = adapter
            viewPager.post{
                viewPager.setCurrentItem(args.pos,false)
            }
            viewPager.getChildAt(args.pos)?.overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        }
//        binding.ivImage.setImageURI(Uri.fromFile(image))
    }

}