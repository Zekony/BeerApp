package com.example.simplebeerapp.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.simplebeerapp.R

@Entity(tableName = "BeerTable")
data class Beer(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    @StringRes val info: Int,
    @DrawableRes val imageResource: Int,
    val beerType: Int,
    val cost: Int,
    var isFavorite: Boolean = false
)

val beers = listOf(
    Beer(1,"Velkopopovicky Kozel ", R.string.kozel_info, R.drawable.kozel, 1, 50),
    Beer(2,"Leffe", R.string.leffe, R.drawable.leffe, 1, 44),
    Beer(3, "Augustiner", R.string.august, R.drawable.augustiner, 2, 120),
    Beer(4, "Paulaner", R.string.paulaner, R.drawable.paulaner_brauerei_munchen, 2, 150),
    Beer(5, "Pilsner", R.string.pilsner, R.drawable.pilsner_urquell, 2, 200),
    Beer(6, "IPA Every Day", R.string.everyday, R.drawable.everyday, 3, 115),
    Beer(7, "White Rabbit", R.string.WhiteRabbrit, R.drawable.whiterabbit, 3, 100),
)

