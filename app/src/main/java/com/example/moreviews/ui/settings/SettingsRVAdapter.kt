package com.example.moreviews.ui.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moreviews.R
import com.example.moreviews.data.SettingItem
import com.example.moreviews.databinding.ItemSettingsBinding
import com.example.moreviews.databinding.ItemSettingsSelectBinding
import java.lang.IllegalArgumentException

class SettingsRVAdapter(private val onClickListener: SettingsViewHolder.OnClickListener) :
    RecyclerView.Adapter<SettingsViewHolder>() {

    var settings = mutableListOf<SettingItem>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    fun updateItem(newItem: SettingItem, position: Int) {
        settings[position] = newItem
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        return when (viewType) {
            R.layout.item_settings_select -> SettingsViewHolder.SelectViewHolder(
                ItemSettingsSelectBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.item_settings -> SettingsViewHolder.ToggleViewHolder(
                ItemSettingsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
            )
            else -> throw IllegalArgumentException("Invalid View Item Provided")
        }
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        when (holder) {
            is SettingsViewHolder.SelectViewHolder -> holder.bind(settings[position] as SettingItem.SelectItem, onClickListener, position)
            is SettingsViewHolder.ToggleViewHolder -> holder.bind(settings[position] as SettingItem.ToggleItem, onClickListener)
        }
    }

    override fun getItemCount(): Int {
        return settings.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (settings[position]) {
            is SettingItem.SelectItem -> R.layout.item_settings_select
            is SettingItem.ToggleItem -> R.layout.item_settings
        }
    }
}