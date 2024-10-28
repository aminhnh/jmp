package com.example.siappilih.database

import androidx.lifecycle.ViewModel

class PemilihViewModel(private val repository: PemilihRepository) : ViewModel() {
    fun insert(pemilih: Pemilih) {
        repository.insert(pemilih)
    }

    fun delete(pemilih: Pemilih) {
        repository.delete(pemilih)
    }

    fun update(pemilih: Pemilih) {
        repository.update(pemilih)
    }
}
