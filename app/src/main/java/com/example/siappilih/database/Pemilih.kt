package com.example.siappilih.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.siappilih.util.JenisKelamin

@Entity(tableName = "pemilih")
data class Pemilih(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Int = 0,

    @ColumnInfo(name = "nik")
    val nik: String,

    @ColumnInfo(name = "nama_lengkap")
    val namaLengkap: String,

    @ColumnInfo(name = "nomor_handphone")
    val nomorHandphone: String,

    @ColumnInfo(name = "is_male")
    val isMale: Boolean,

    @ColumnInfo(name = "tanggal_pendataan")
    val tanggalPendataan: String,

    @ColumnInfo(name = "alamat")
    val alamat: String,

    @ColumnInfo(name = "latitude")
    val latitude: Double,

    @ColumnInfo(name = "longitude")
    val longitude: Double,

    @ColumnInfo(name = "foto")
    val foto: String,
)