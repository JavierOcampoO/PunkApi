package com.test.punkapi.data.model

import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "beers")
class Beer(

    @PrimaryKey
    val id: Int,
    val name: String,
    val tagline: String,
    @SerializedName("first_brewed") val firstBrewed: String,
    val description: String,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("brewers_tips") val brewersTips: String?,
    @SerializedName("contributed_by") val contributedBy: String?,

    @SerializedName("food_pairing") val foodPairing: List<String>?

): Serializable {


    @androidx.room.Dao
    interface Dao{

        @Query("SELECT * FROM beers")
        fun all(): List<Beer>

        @Query("SELECT * FROM beers WHERE id = :id")
        operator fun get(id: Long): Beer

        @Query("DELETE FROM beers")
        fun deleteAll(): Int

        @Delete
        fun delete(beer: Beer): Int

        @Update
        fun update(beer: Beer): Int

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insert(beers: List<Beer>)

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insert(beer: Beer): Long

    }

}