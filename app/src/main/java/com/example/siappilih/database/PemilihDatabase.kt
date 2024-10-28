package com.example.siappilih.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.siappilih.util.Converters

@Database(entities = [Pemilih::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PemilihDatabase : RoomDatabase() {
    abstract fun pemilihDao() : PemilihDao?
    companion object {
        @Volatile
        private var INSTANCE: PemilihDatabase? = null
        fun getDatabase(context: Context): PemilihDatabase? {
            if (INSTANCE == null) {
                synchronized(PemilihDatabase::class.java) {
                    INSTANCE = databaseBuilder(context.applicationContext,
                            PemilihDatabase::class.java,
                            "note_database").build()
                }
            }
            return INSTANCE
        }
    }
}