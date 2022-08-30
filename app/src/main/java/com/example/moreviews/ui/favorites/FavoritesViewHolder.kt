package com.example.moreviews.ui.favorites

import androidx.recyclerview.widget.RecyclerView
import com.example.moreviews.R
import com.example.moreviews.data.FavoriteData
import com.example.moreviews.databinding.FavoritesItemBinding

class FavoritesViewHolder(private val binding: FavoritesItemBinding, private val onClickListener: OnClickListener) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: FavoriteData, onClickListener: OnClickListener) {
        binding.tvTitle.text = item.title
        binding.tvDescription.text = item.description
        binding.ivFavorites.setImageResource(item.imageId)
        if (item.liked) {
            binding.ibLike.setImageResource(R.drawable.ic_like)
        }
        binding.ibLike.setOnClickListener {
            onClickListener.onLikeClick(binding, item)
        }
        binding.ibShare.setOnClickListener {
            onClickListener.onShareClick(binding, item)
        }
    }

    interface OnClickListener {
        fun onLikeClick(binding: FavoritesItemBinding, item: FavoriteData)
        fun onShareClick(binding: FavoritesItemBinding, item: FavoriteData)
    }

}