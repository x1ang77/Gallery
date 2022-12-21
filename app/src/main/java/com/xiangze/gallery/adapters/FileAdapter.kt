package com.xiangze.gallery.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xiangze.gallery.R
import com.xiangze.gallery.databinding.ItemLayoutFileBinding
import java.io.File

class FileAdapter(var files: List<File>) :
    RecyclerView.Adapter<FileAdapter.FileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val binding =
            ItemLayoutFileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        val item = files[position]
        holder.binding.run {
            val uri = Uri.fromFile(item)
            ivFile.setImageURI(uri)
            tvFileName.text = item.name
        }
    }

    override fun getItemCount() = files.size

    class FileViewHolder(val binding: ItemLayoutFileBinding) : RecyclerView.ViewHolder(binding.root)

}