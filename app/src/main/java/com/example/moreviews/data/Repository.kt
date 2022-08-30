package com.example.moreviews.data

class Repository {
    private val settingsData = SettingItem.getSettings()
    private val favoritesData = FavoriteData.getFavorites()

    fun getSettingsData() : MutableList<SettingItem> = settingsData
    fun getFavorites() : List<FavoriteData> = favoritesData

    companion object {
        private var REPOSITORY: Repository? = null

        fun getInstance() = REPOSITORY ?: Repository().also { REPOSITORY = it }
    }

}