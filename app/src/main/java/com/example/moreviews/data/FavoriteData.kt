package com.example.moreviews.data

import com.example.moreviews.R

data class FavoriteData(
    val title: String,
    val description: String,
    val imageId: Int,
    var liked: Boolean = false
) {
    companion object {
        fun getFavorites(): List<FavoriteData> {
            return listOf(
                FavoriteData(
                 "Meet Kermit !!!",
                 "Kermit is a green frog that is a rising shining star of the upcoming century. He has immense talent to depict emotions that are hard to describe",
                 R.drawable.kermit_01_portrait
                ),
                FavoriteData(
                    "Parting ways",
                    "Kermit must part ways with his friends in order to explore his own path. Don't be sad because it is over, smile because it happened",
                    R.drawable.kermit_02_friends,
                    liked = true
                ),
                FavoriteData(
                    "Hello loneliness my best friend",
                    "Losing contact with friends was too much for poor kermit to handle. He is now a drunkard who lives within his delusion of the things he achieved in his life, but in reality he is just feeding off his parents money like a parasite",
                    R.drawable.kermit_03_drunk
                    ),
                FavoriteData(
                    "Kermit arrested for drugs",
                    "Having lost his way, kermit was found to be consuming illegal drugs. Reports suggests that he also partook in their smuggling. Kermit is now sentenced to 4 years of Jail."
                    ,R.drawable.kermit_04_high
                ),
                FavoriteData(
                    "Turning Over a new leaf",
                    "Down the streets of countryside, amongst desolated towns, you will find a wandering sitar artist. He spends his time playing music in front of Chapel, and lives off the money people give him for his soothing music",
                    R.drawable.kermit_05_music,
                    liked = true
                )

            )
        }
    }
}