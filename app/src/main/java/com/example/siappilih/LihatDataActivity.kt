package com.example.siappilih

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.siappilih.adapter.PemilihAdapter
import com.example.siappilih.database.PemilihDao
import com.example.siappilih.database.PemilihDatabase
import com.example.siappilih.databinding.ActivityFormEntryBinding
import com.example.siappilih.databinding.ActivityLihatDataBinding
import com.example.siappilih.util.AppExecutorService
import java.util.concurrent.ExecutorService

class LihatDataActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLihatDataBinding.inflate(layoutInflater)
    }
    private lateinit var executorService: ExecutorService
    private lateinit var pemilihDao : PemilihDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // TODO : refactor to use ViewModel
        executorService = AppExecutorService.executorService

        val db = PemilihDatabase.getDatabase(this)
        pemilihDao = db!!.pemilihDao()!!

        with(binding) {
            getAllPemilih()
        }
    }
    private fun getAllPemilih(keyword : String = "") {
        pemilihDao.getAll.observe(this) { pemilihs ->
            val adapterPemilih = PemilihAdapter(pemilihs) { pemilih ->
                val intentToEditActivity = Intent(this@LihatDataActivity, LihatDataPemilihActivity::class.java)
                intentToEditActivity.putExtra("id", pemilih.id)

                startActivity(intentToEditActivity)
            }
            binding.rvPemilih.apply {
                adapter = adapterPemilih
                layoutManager = LinearLayoutManager(this@LihatDataActivity)
            }
        }
    }
    override fun onResume() {
        super.onResume()
        getAllPemilih()
    }
}