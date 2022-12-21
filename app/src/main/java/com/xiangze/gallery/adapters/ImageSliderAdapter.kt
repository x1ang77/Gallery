package com.xiangze.gallery.adapters

import android.media.Image
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xiangze.gallery.databinding.ImageSlideBinding
import java.io.File

class ImageSliderAdapter(var items: List<File>): RecyclerView.Adapter<ImageSliderAdapter.ImageViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ImageSlideBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = items[position]
        holder.binding.ivImage.setImageURI(Uri.fromFile(image))
    }

    override fun getItemCount() = items.size

    class ImageViewHolder(val binding: ImageSlideBinding): RecyclerView.ViewHolder(binding.root)

}