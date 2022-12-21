package com.xiangze.gallery.adapters

import android.media.Image
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.xiangze.gallery.MainActivity
import com.xiangze.gallery.databinding.ItemLayoutImageBinding
import com.xiangze.gallery.fragments.HomeFragmentDirections
import com.xiangze.gallery.fragments.ImageViewerFragment
import java.io.File

class ImageSliderAdapter(var items: List<File>) :
    RecyclerView.Adapter<ImageSliderAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding =
            ItemLayoutImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = items[position]
        holder.binding.ivImage.setImageURI(Uri.fromFile(item))
    }

    override fun getItemCount() = items.size

    class ImageViewHolder(val binding: ItemLayoutImageBinding) :
        RecyclerView.ViewHolder(binding.root)
}