package com.example.siappilih.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class PemilihViewModel(private val repository: PemilihRepository) : ViewModel() {
    val allPemilih: LiveData<List<Pemilih>> = repository.allPemilih
    fun insert(pemilih: Pemilih) {
        repository.insert(pemilih)
    }

    fun delete(pemilih: Pemilih) {
        repository.delete(pemilih)
    }

    fun update(pemilih: Pemilih) {
        repository.update(pemilih)
    }

    fun getOneById(id: Int): LiveData<Pemilih> {
        return repository.getOneById(id)
    }
}
