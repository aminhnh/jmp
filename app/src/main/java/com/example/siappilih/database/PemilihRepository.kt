package com.example.siappilih.database

import com.example.siappilih.util.AppExecutorService
import java.util.concurrent.Executors

class PemilihRepository(private val pemilihDao: PemilihDao) {

    // Insert a pemilih record
    fun insert(pemilih: Pemilih) {
        AppExecutorService.executorService.execute {
            pemilihDao.insert(pemilih)
        }
    }

    // Delete a pemilih record
    fun delete(pemilih: Pemilih) {
        AppExecutorService.executorService.execute {
            pemilihDao.delete(pemilih)
        }
    }

    // Update a pemilih record
    fun update(pemilih: Pemilih) {
        AppExecutorService.executorService.execute {
            pemilihDao.update(pemilih)
        }
    }
}
