package com.example.moreviews.ui.settings

import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.moreviews.data.SettingItem
import com.example.moreviews.databinding.ItemSettingsBinding
import com.example.moreviews.databinding.ItemSettingsSelectBinding
import com.example.moreviews.ui.utils.BottomSheetDialog
import java.text.FieldPosition

sealed class SettingsViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    class ToggleViewHolder(private val binding: ItemSettingsBinding) : SettingsViewHolder(binding) {
        fun bind(toggleSetting: SettingItem.ToggleItem, onClickListener: OnClickListener) {
            binding.tvSettingsTitle.text = toggleSetting.title
            binding.tvSettingsDescription.text = toggleSetting.description
            binding.switchSettings.isChecked = toggleSetting.toggle

            binding.root.setOnClickListener {
                onClickListener.onToggleViewClick(binding, toggleSetting)
            }
            binding.switchSettings.setOnClickListener {
                onClickListener.onToggleClick(binding, toggleSetting)
            }
        }
    }

    class SelectViewHolder(private val binding: ItemSettingsSelectBinding) :
        SettingsViewHolder(binding) {
        fun bind(selectSetting: SettingItem.SelectItem, onClickListener: OnClickListener, parentPosition: Int) {
            binding.tvSettingsTitle.text = selectSetting.title
            binding.tvSettingsDescription.text = selectSetting.description
            binding.chipSettings.text = selectSetting.options[selectSetting.selected]

            binding.chipSettings.setOnClickListener {
                onClickListener.onChipClick(binding, selectSetting, parentPosition)
            }
        }
    }

    public interface OnClickListener {

        fun onToggleClick(viewBinding: ItemSettingsBinding, item: SettingItem.ToggleItem)
        fun onToggleViewClick(viewBinding: ItemSettingsBinding, item: SettingItem.ToggleItem)
        fun onChipClick(viewBinding: ItemSettingsSelectBinding, item: SettingItem.SelectItem, parentPosition: Int)

    }
}