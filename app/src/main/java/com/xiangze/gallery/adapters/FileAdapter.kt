package com.xiangze.gallery.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xiangze.gallery.databinding.ItemLayoutFileBinding
import java.io.File

class FileAdapter(
    var files: List<File>,
    val onClick: (position: Int) -> Unit
) :
    RecyclerView.Adapter<FileAdapter.FileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val binding = ItemLayoutFileBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        val image = files[position]
        holder.binding.run {
            iv.setImageURI(Uri.fromFile(image))

            cvImage.setOnClickListener{
                onClick(position)
            }
        }
    }

    override fun getItemCount() = files.size

    class FileViewHolder(val binding: ItemLayoutFileBinding) : RecyclerView.ViewHolder(binding.root)
}