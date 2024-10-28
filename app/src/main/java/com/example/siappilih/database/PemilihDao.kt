package com.example.siappilih.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface PemilihDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(pemilih: Pemilih)

    @Update
    fun update(pemilih: Pemilih)

    @Delete
    fun delete(pemilih: Pemilih)

    @get:Query("SELECT * from pemilih ORDER BY id ASC")
    val allPemilih : LiveData<List<Pemilih>>

    @Query("SELECT * FROM pemilih WHERE id = :pemilihId")
    fun getPemilihById(pemilihId: Int): LiveData<Pemilih>

    @Query("DELETE FROM pemilih WHERE id = :pemilihId")
    fun deleteById(pemilihId: Int)
}