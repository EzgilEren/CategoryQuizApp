package com.ezgieren.categoryquizapp.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ezgieren.categoryquizapp.data.model.CategoryDto
import com.ezgieren.categoryquizapp.databinding.CategoryItemBinding

class CategoryAdapter(
    private val onItemClick: (CategoryDto) -> Unit
) : ListAdapter<CategoryDto, CategoryAdapter.CategoryViewHolder>(CategoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CategoryViewHolder(private val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CategoryDto) {
            binding.tvCategoryTitle.text = item.title
            Glide.with(binding.ivCategory.context)
                .load(item.image.url)
                .into(binding.ivCategory)

            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    class CategoryDiffCallback : DiffUtil.ItemCallback<CategoryDto>() {
        override fun areItemsTheSame(oldItem: CategoryDto, newItem: CategoryDto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CategoryDto, newItem: CategoryDto): Boolean {
            return oldItem == newItem
        }
    }
}