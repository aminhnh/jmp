package com.example.siappilih

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.siappilih.database.Pemilih
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

        val pemilihId = intent.getIntExtra("id", 0)
        pemilihViewModel.getOneById(pemilihId).observe(this) { pemilih ->
            populatePemilih(pemilih)
            with(binding) {
                btnDeletePemilih.setOnClickListener {
                    showConfirmationDialog {
                        deletePemilih(pemilih)
                    }
                }
            }
        }
    }
    private fun populatePemilih(pemilih: Pemilih) {
        with(binding) {
            // TODO add the rest of the data
            txtNoHp.text = pemilih.nomorHandphone
        }
    }
    private fun showConfirmationDialog(onConfirm: () -> Unit) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Konfirmasi Tindakan")
        builder.setMessage("Apakah Anda yakin ingin menghapus data pemilih ini?")

        builder.setPositiveButton("Ya") { dialog: DialogInterface, _: Int ->
            onConfirm()
            dialog.dismiss()
        }

        builder.setNegativeButton("Tidak") { dialog: DialogInterface, _: Int ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun deletePemilih(pemilih: Pemilih) {
        val namaLengkap = pemilih.namaLengkap
        pemilihViewModel.delete(pemilih)
        Toast.makeText(this, "Berhasil menghapus data pemilih bernama $namaLengkap", Toast.LENGTH_LONG).show()
        returnToLihatDataActivity()
    }

    private fun returnToLihatDataActivity() {
        val intentToMainActivity = Intent(this@LihatDataPemilihActivity, LihatDataActivity::class.java)
        startActivity(intentToMainActivity)
        finish()
    }
}