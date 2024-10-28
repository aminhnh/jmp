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

        @ColumnInfo(name = "jenis_kelamin")
    val jenisKelamin: JenisKelamin,

        @ColumnInfo(name = "tanggal_pendataan")
    val tanggalPendataan: String,

        @ColumnInfo(name = "lokasi_rumah")
    val lokasiRumah: String
)