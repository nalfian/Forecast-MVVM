package com.nalfian.forecast.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nalfian.forecast.data.db.entity.CurrentWeather


@Database(
    entities = [CurrentWeather::class],
    version = 1
)
abstract class ForecastDatabase : RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherDao

    companion object{
        @Volatile private var instace: ForecastDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instace?: synchronized(LOCK){
            instace ?: buildDatabase(context).also { instace = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ForecastDatabase::class.java, "forecast.db")
                .build()
    }
}