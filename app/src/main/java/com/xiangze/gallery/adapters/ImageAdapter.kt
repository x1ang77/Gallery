package com.xiangze.gallery.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xiangze.gallery.databinding.ItemLayoutBinding
import java.io.File

class ImageAdapter(
    var images: List<File>,
    val onClick: (position: Int) -> Unit
) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    class ImageViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding =
            ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = images[position]
        holder.binding.run {
            ivImage.setImageURI(Uri.fromFile(image))
//            tvFileName.text = image.name
            cvImage.setOnClickListener {
                onClick(position)
            }
//            llFile.setOnClickListener {
//                onClick(image)
//            }
        }
    }

    override fun getItemCount() = images.size
}