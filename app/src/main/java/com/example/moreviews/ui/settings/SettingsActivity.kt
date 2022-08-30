package com.example.moreviews.ui.settings

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moreviews.R
import com.example.moreviews.data.Repository
import com.example.moreviews.data.SettingItem
import com.example.moreviews.databinding.ActivitySettingsBinding
import com.example.moreviews.databinding.BottomSheetItemBinding
import com.example.moreviews.databinding.ItemSettingsBinding
import com.example.moreviews.databinding.ItemSettingsSelectBinding
import com.example.moreviews.ui.utils.BottomSheetAdapter
import com.example.moreviews.ui.utils.BottomSheetDialog

class SettingsActivity : AppCompatActivity(), SettingsViewHolder.OnClickListener,
    BottomSheetAdapter.OnClickListener {
    private lateinit var binding: ActivitySettingsBinding
    private var lastSelected: Int = 0
    private val settingsRVAdapter = SettingsRVAdapter(this)
    private val repository = Repository.getInstance()
    private val bottomSheetDialog = BottomSheetDialog(this, settingsRVAdapter)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingsRVAdapter.settings = repository.getSettingsData()

        binding.rvSettings.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@SettingsActivity)
            adapter = settingsRVAdapter
        }

    }

    override fun onToggleClick(viewBinding: ItemSettingsBinding, item: SettingItem.ToggleItem) {
        viewBinding.switchSettings.isChecked = item.toggle.let {
            item.toggle = !it
            !it
        }
    }

    override fun onToggleViewClick(viewBinding: ItemSettingsBinding, item: SettingItem.ToggleItem) {
        viewBinding.switchSettings.isChecked = item.toggle.let {
            item.toggle = !it
            !it
        }
    }

    override fun onChipClick(viewBinding: ItemSettingsSelectBinding, item: SettingItem.SelectItem, parentPosition: Int) {
        bottomSheetDialog.updateSelectItems(item, parentPosition)
        lastSelected = parentPosition
        bottomSheetDialog.show(supportFragmentManager, "Bottom Sheet")
    }

    override fun onSelectClick(
        viewBinding: BottomSheetItemBinding,
        item: SettingItem.SelectItem,
        position: Int
    ) {
        val lastChecked = item.selected
        item.selected = position
        bottomSheetDialog.updateSelectItemChange(item, position, lastChecked, lastSelected)

    }


}