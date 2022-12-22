package com.xiangze.gallery.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xiangze.gallery.databinding.LayoutImageFullBinding
import java.io.File

class ImageSliderAdapter(var images: List<File>) :
    RecyclerView.Adapter<ImageSliderAdapter.ImageViewHolder>() {
    class ImageViewHolder(val binding: LayoutImageFullBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding =
            LayoutImageFullBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = images[position]
        holder.binding.ivImage.setImageURI(Uri.fromFile(image))
    }

    override fun getItemCount() = images.size
}