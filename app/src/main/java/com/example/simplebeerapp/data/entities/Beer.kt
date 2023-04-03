package com.example.simplebeerapp.data.entities

import androidx.annotation.DrawableRes
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.simplebeerapp.R

@Entity(tableName = "BeerTable")
data class Beer(
    @PrimaryKey
    val id: Int,
    val name: String,
    val info: Int,
    @DrawableRes val imageResource: Int,
    val beerType: String,
    val cost: Int,
    var isFavorite: Boolean = false
) {
    companion object {
        val beers = listOf(
            Beer(1, "Velkopopovicky Kozel ", R.string.kozel_info, R.drawable.kozel, "Dark" , 50),
            Beer(2, "Leffe", R.string.leffe, R.drawable.leffe, "Dark", 44),
            Beer(3, "Augustiner", R.string.august, R.drawable.augustiner, "Light", 120),
            Beer(4, "Paulaner", R.string.paulaner, R.drawable.paulaner_brauerei_munchen, "Light", 150),
            Beer(5, "Pilsner", R.string.pilsner, R.drawable.pilsner_urquell, "Light", 200),
            Beer(6, "IPA Every Day", R.string.everyday, R.drawable.everyday, "IPA", 115),
            Beer(7, "White Rabbit", R.string.WhiteRabbrit, R.drawable.whiterabbit, "IPA", 100),
        )
    }
}





