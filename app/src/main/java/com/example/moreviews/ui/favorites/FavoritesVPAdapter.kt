package com.example.moreviews.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moreviews.data.FavoriteData
import com.example.moreviews.databinding.FavoritesItemBinding

class FavoritesVPAdapter(private val favorites: List<FavoriteData>, private val onClickListener: FavoritesViewHolder.OnClickListener): RecyclerView.Adapter<FavoritesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder(
            FavoritesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickListener
        )
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(favorites[position], onClickListener)
    }

    override fun getItemCount(): Int = favorites.size

}