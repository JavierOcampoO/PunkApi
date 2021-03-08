package com.test.punkapi.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.test.punkapi.data.database.dataconverters.BeerDataConverter
import com.test.punkapi.data.model.Beer

@Database(
    entities = [
        Beer::class
    ],
    version = 3,
    exportSchema = false
)
@TypeConverters(BeerDataConverter::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun beers(): Beer.Dao

    @DatabaseEnviroment(name = "beers-db")
    companion object {

        fun getDatabaseEnviromentName(): String = this::class.java.getAnnotation(DatabaseEnviroment::class.java)!!.name

        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            var instance = INSTANCE

            if (instance == null) {
                instance = buildDatabase(context)
                INSTANCE = instance
            }
            return instance
        }

        operator fun invoke(context: Context) = getInstance(context)

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context, AppDatabase::class.java, getDatabaseEnviromentName())
            .fallbackToDestructiveMigration()
            .build()

        public fun nullDatabase(){
            INSTANCE = null
        }
    }
}