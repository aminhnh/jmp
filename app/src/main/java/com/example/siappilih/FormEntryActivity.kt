package com.example.siappilih

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.room.ColumnInfo
import com.example.siappilih.database.Pemilih
import com.example.siappilih.database.PemilihDao
import com.example.siappilih.database.PemilihDatabase
import com.example.siappilih.database.PemilihRepository
import com.example.siappilih.database.PemilihViewModel
import com.example.siappilih.database.ViewModelFactory
import com.example.siappilih.databinding.ActivityFormEntryBinding
import com.example.siappilih.databinding.ActivityMainBinding
import com.example.siappilih.util.AppExecutorService
import com.example.siappilih.util.JenisKelamin
import java.util.Calendar
import java.util.concurrent.ExecutorService

class FormEntryActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityFormEntryBinding.inflate(layoutInflater)
    }
    private lateinit var pemilihViewModel: PemilihViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val repository = PemilihRepository(PemilihDatabase.getDatabase(this)!!.pemilihDao()!!)
        pemilihViewModel = ViewModelProvider(this, ViewModelFactory(repository)).get(PemilihViewModel::class.java)


        with(binding) {
            inputTanggal.setOnClickListener {
                showDatePicker(inputTanggal)
            }
            btnSelectLocation.setOnClickListener {
                openMapForLocationSelection()
            }
            btnSavePemilih.setOnClickListener {
                savePelimih();
            }
        }
    }

    private fun showDatePicker(editText: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val formattedDate = String.format("%02d/%02d/%d", selectedDay, selectedMonth + 1, selectedYear)
                editText.setText(formattedDate)
            },
            year, month, day
        )
        datePickerDialog.show()
    }

    private fun openMapForLocationSelection() {
    }

    private fun savePelimih() {
        with(binding) {
            val isMale: Boolean = when (genderGroup.checkedRadioButtonId) {
                R.id.radioMale -> true      // Laki-Laki
                R.id.radioFemale -> false   // Perempuan
                else -> true
            }

            val newPemilih = Pemilih(
                nik = inputNik.text.toString(),
                namaLengkap = inputNama.text.toString(),
                nomorHandphone = inputNoHp.text.toString(),
                isMale = isMale,
                tanggalPendataan = inputTanggal.text.toString().trim(),
                alamat = inputAlamat.text.toString(),
                latitude = 1.0,
                longitude = 1.0,
                foto = "",
            )
            insertPemilih(newPemilih)
        }
    }

    private fun validateData(): Boolean {
        with(binding) {
            // TODO : ADD VALIDATION
            return true
        }
    }

    private fun insertPemilih(pemilih: Pemilih) {
        if (pemilih.nik.isEmpty() && pemilih.namaLengkap.isEmpty() ){
            Toast.makeText(this, "Tidak dapat menyimpan pemilih", Toast.LENGTH_LONG).show()
            returnToMainActivity()
        } else {
            try {
                pemilihViewModel.insert(pemilih)
                Toast.makeText(this, "Berhasil menyimpan data pemilih", Toast.LENGTH_LONG).show()
                returnToMainActivity()
            } catch (e: Exception) {
                Toast.makeText(this, "Gagal menyimpan data pemilih", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun returnToMainActivity() {
        val intentToMainActivity = Intent(this, MainActivity::class.java)
        startActivity(intentToMainActivity)
        finish()
    }
}