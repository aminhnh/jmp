package com.example.siappilih.database

import androidx.lifecycle.LiveData
import com.example.siappilih.util.AppExecutorService
import java.util.concurrent.Executors

class PemilihRepository(private val pemilihDao: PemilihDao) {
    val allPemilih: LiveData<List<Pemilih>> = pemilihDao.getAll

    fun insert(pemilih: Pemilih) {
        AppExecutorService.executorService.execute {
            pemilihDao.insert(pemilih)
        }
    }

    fun delete(pemilih: Pemilih) {
        AppExecutorService.executorService.execute {
            pemilihDao.delete(pemilih)
        }
    }

    fun update(pemilih: Pemilih) {
        AppExecutorService.executorService.execute {
            pemilihDao.update(pemilih)
        }
    }

    fun getOneById(id: Int): LiveData<Pemilih> {
        return pemilihDao.getOneById(id)
    }
}
