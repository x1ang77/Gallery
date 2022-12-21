package com.xiangze.gallery.adapter

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xiangze.gallery.databinding.ItemLayoutImageBinding
import java.io.File

class FileAdapter(
    var images:List<File>,
    val onClick:(position:Int)-> Unit
):RecyclerView.Adapter<FileAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding= ItemLayoutImageBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = images[position]
        holder.binding.run {
            if (item.isFile) {
                ivImage.setImageURI(Uri.fromFile(item))
                cvImage.setOnClickListener{
                    onClick(position)
                }
            }
        }
    }

    override fun getItemCount() =images.size

    class ImageViewHolder(val binding:ItemLayoutImageBinding):RecyclerView.ViewHolder(binding.root)
}