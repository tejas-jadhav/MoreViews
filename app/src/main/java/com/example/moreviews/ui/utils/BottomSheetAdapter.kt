package com.example.moreviews.ui.utils

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.moreviews.R
import com.example.moreviews.data.SettingItem
import com.example.moreviews.databinding.BottomSheetItemBinding

class BottomSheetAdapter(private val onClickListener: OnClickListener) :
    RecyclerView.Adapter<BottomSheetAdapter.BottomSheetViewHolder>() {

    var selectItem : SettingItem.SelectItem = SettingItem.SelectItem("Hello", "Lorem Porem")
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    inner class BottomSheetViewHolder(private val binding: BottomSheetItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            settingItem: SettingItem.SelectItem,
            position: Int,
            onClickListener: OnClickListener
        ) {
            binding.tvBottomSheetItem.text = settingItem.options[position]
            if (position == selectItem.selected) {
                binding.ivCheck.visibility = View.VISIBLE
            } else {
                binding.ivCheck.visibility = View.INVISIBLE
            }
            binding.root.setOnClickListener {
                onClickListener.onSelectClick(binding, settingItem, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomSheetViewHolder {
        val view = BottomSheetViewHolder(
            BottomSheetItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        return view
    }

    override fun onBindViewHolder(holder: BottomSheetViewHolder, position: Int) {
        holder.bind(selectItem, position, onClickListener)
    }

    override fun getItemCount(): Int {
        return selectItem.options.size
    }

    interface OnClickListener {
        fun onSelectClick(viewBinding: BottomSheetItemBinding, item: SettingItem.SelectItem, position: Int)
    }

}