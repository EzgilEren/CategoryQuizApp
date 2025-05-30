package com.ezgieren.plantidentifyapp.ui.paywall

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ezgieren.plantidentifyapp.databinding.ItemCategoryBinding
import com.ezgieren.plantidentifyapp.R
import com.ezgieren.plantidentifyapp.domain.model.Category

class CategoryAdapter(
    private val onItemClick: (Category) -> Unit
) : ListAdapter<Category, CategoryAdapter.CategoryViewHolder>(CategoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Category) {
            binding.tvCategoryTitle.text = item.title

            val imageUrl = item.imageUrl
            if (imageUrl.isNotEmpty()) {
                Glide.with(binding.ivCategory.context)
                    .load(imageUrl)
                    .centerCrop()
                    .placeholder(R.drawable.bg_image_placeholder) // fallback image
                    .into(binding.ivCategory)
            } else {
                binding.ivCategory.setImageResource(R.drawable.bg_image_placeholder)
            }

            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    class CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }
}