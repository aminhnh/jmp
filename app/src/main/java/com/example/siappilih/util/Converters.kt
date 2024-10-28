package com.example.siappilih.util

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromJenisKelamin(jenisKelamin: JenisKelamin): String {
        return jenisKelamin.name
    }

    @TypeConverter
    fun toJenisKelamin(jenisKelamin: String): JenisKelamin {
        return JenisKelamin.valueOf(jenisKelamin)
    }
}
