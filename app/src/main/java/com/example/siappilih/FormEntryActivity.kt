package com.example.siappilih

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.siappilih.database.PemilihDao
import com.example.siappilih.databinding.ActivityFormEntryBinding
import com.example.siappilih.databinding.ActivityMainBinding
import java.util.Calendar
import java.util.concurrent.ExecutorService

class FormEntryActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityFormEntryBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            inputTanggal.setOnClickListener {
                showDatePicker(inputTanggal)
            }
            btnSelectLocation.setOnClickListener {
                openMapForLocationSelection()
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
                // Update the EditText with the selected date (format: DD/MM/YYYY)
                val formattedDate = String.format("%02d/%02d/%d", selectedDay, selectedMonth + 1, selectedYear)
                editText.setText(formattedDate)
            },
            year, month, day
        )
        datePickerDialog.show()
    }

    private fun openMapForLocationSelection() {
    }
}