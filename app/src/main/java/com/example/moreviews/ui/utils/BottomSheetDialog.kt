package com.example.moreviews.ui.utils



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moreviews.R
import com.example.moreviews.data.SettingItem
import com.example.moreviews.databinding.BottomSheetLayoutBinding
import com.example.moreviews.ui.settings.SettingsRVAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.FieldPosition

class BottomSheetDialog(onClickListener: BottomSheetAdapter.OnClickListener, private val settingsRVAdapter: SettingsRVAdapter) : BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetLayoutBinding
    private var mAdapter = BottomSheetAdapter(onClickListener)
    private lateinit var selectItem : SettingItem.SelectItem


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_layout, container, false)
        binding = BottomSheetLayoutBinding.bind(view)
        binding.rvBottomSheet.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context)
        }
        return binding.root
    }

    fun updateSelectItems(item: SettingItem.SelectItem, position: Int) {
        selectItem = item
        mAdapter.selectItem = selectItem
        settingsRVAdapter.updateItem(item, position)
        mAdapter.notifyDataSetChanged()
    }

    fun updateSelectItemChange(item: SettingItem.SelectItem, position: Int, lastChecked: Int, parentPosition: Int) {
        selectItem.selected = position
        mAdapter.selectItem = selectItem
        mAdapter.notifyDataSetChanged()
        updateSelectItems(selectItem, parentPosition)
    }
}