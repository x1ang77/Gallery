package com.xiangze.gallery.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xiangze.gallery.R
import com.xiangze.gallery.databinding.ItemLayoutBinding
import com.xiangze.gallery.databinding.ItemLayoutFileBinding
import java.io.File

class FileAdapter(
    var files: List<File>,
    val onClick: (position: Int) -> Unit
) :
    RecyclerView.Adapter<FileAdapter.FileViewHolder>() {
    class FileViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val binding =
            ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        val file = files[position]
        holder.binding.run {
            ivFile.setImageURI(Uri.fromFile(file))
//            tvFileName.text = file.name
            cvFile.setOnClickListener {
                onClick(position)
            }
//            llFile.setOnClickListener {
//                onClick(file)
//            }
        }
    }

    override fun getItemCount() = files.size
}