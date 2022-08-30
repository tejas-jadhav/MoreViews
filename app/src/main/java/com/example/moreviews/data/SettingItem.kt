package com.example.moreviews.data

sealed class SettingItem(val title: String, val description: String) {
    class ToggleItem(title: String, description: String, var toggle: Boolean = false) :
        SettingItem(title, description)

    class SelectItem(
        title: String,
        description: String,
        val options: List<String> = listOf("Lorem", "Porem"),
        var selected: Int = 0
    ) : SettingItem(title, description)

    companion object {
        fun getSettings(): MutableList<SettingItem> = mutableListOf(
            ToggleItem(
                "Notifications",
                "Receive notifications. Of course we don't send any notifications to begin with.",
                toggle = false
            ),
            ToggleItem(
                "Read all your private information",
                "We intend to sell your information to third party sites to gain a massive profit.",
                toggle = true
            ),
            SelectItem(
                "Animals you like",
                "Everyone loves animals ! ... Right ??",
                options = listOf("Cats", "Dogs", "I am a monster"),
                selected = 0
            ),
            ToggleItem(
                "Raise to wake",
                "Pick up your phone and it will automatically wake up",
                toggle = true
            ),
            SelectItem(
                "Lock Screen Clock format",
                "Choose clock format of your preference",
                options = listOf("Analog", "Digital", "Digital (Extra Special)"),
                selected = 2
            ),
            ToggleItem(
                "Read Receipts",
                "If turned off, you won't send or receive Read Receipts. Read Receipts are always sent for group chats. Do you really wish to live knowing that you are being ignored ?",
                toggle = false
            ),
            SelectItem(
                "Juan",
                "Juan",
                options = listOf("Juan", "Juan", "Juan"), selected = 1
            ),
            SelectItem(
                "Will a bottom card appear ?",
                "Click to check it out",
                options = listOf(
                    "It does appear",
                    "It is an illusion",
                    "This is CGI",
                    "Its broken"
                ),
                selected = 1
            ),
        )
    }
}

