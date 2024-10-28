package com.example.siappilih

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.siappilih.database.PemilihDao
import com.example.siappilih.database.PemilihDatabase
import com.example.siappilih.database.PemilihRepository
import com.example.siappilih.database.PemilihViewModel
import com.example.siappilih.database.ViewModelFactory
import com.example.siappilih.databinding.ActivityLihatDataBinding
import com.example.siappilih.databinding.ActivityLihatDataPemilihBinding
import com.example.siappilih.util.AppExecutorService
import java.util.concurrent.ExecutorService

class LihatDataPemilihActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLihatDataPemilihBinding.inflate(layoutInflater)
    }
    private lateinit var pemilihViewModel: PemilihViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val repository = PemilihRepository(PemilihDatabase.getDatabase(this)!!.pemilihDao()!!)
        pemilihViewModel = ViewModelProvider(this, ViewModelFactory(repository)).get(PemilihViewModel::class.java)

        with(binding) {
            populatePemilih()
        }
    }
    private fun populatePemilih() {

    }
}