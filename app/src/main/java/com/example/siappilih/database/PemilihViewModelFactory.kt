package com.example.siappilih.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val repository: PemilihRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PemilihViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PemilihViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

