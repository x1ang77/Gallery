package com.xiangze.gallery.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xiangze.gallery.databinding.FragmentImageViewerBinding
import com.xiangze.gallery.databinding.ImageLayoutImageBinding
import java.io.File

class ImageSliderAdapter(
    private var items: List<File>
) : RecyclerView.Adapter<ImageSliderAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ImageLayoutImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = items[position]
        holder.binding.ivImage.setImageURI(Uri.fromFile(item))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ImageViewHolder(val binding: ImageLayoutImageBinding) :
        RecyclerView.ViewHolder(binding.root)
}