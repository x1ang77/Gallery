package com.xiangze.gallery.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xiangze.gallery.databinding.ItemLayoutFileBinding
import java.io.File

class GalleryAdapter(var files: List<File>):RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {
    class GalleryViewHolder(val binding: ItemLayoutFileBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding = ItemLayoutFileBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GalleryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val file = files[position]
        holder.binding.run {
            ivImage.setImageURI(Uri.fromFile(file))
        }
    }

    override fun getItemCount() = files.size

}