package com.example.siappilih

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.siappilih.database.Pemilih
import com.example.siappilih.database.PemilihDatabase
import com.example.siappilih.database.PemilihRepository
import com.example.siappilih.database.PemilihViewModel
import com.example.siappilih.database.ViewModelFactory
import com.example.siappilih.databinding.ActivityFormEntryBinding
import java.io.File
import java.io.FileOutputStream
import java.util.Calendar
import android.Manifest
import androidx.core.content.FileProvider
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class FormEntryActivity : AppCompatActivity() {
    private val TAG = "FormEntryActivity"
    private val binding by lazy {
        ActivityFormEntryBinding.inflate(layoutInflater)
    }
    private lateinit var pemilihViewModel: PemilihViewModel

    private var imagePath: String? = null

    private val requestCameraPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        if (granted) captureImage()
    }

    private val selectImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { handleImageResult(it) }
    }

    private val takePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) {
            imagePath?.let { binding.imagePreview.setImageURI(Uri.parse(it)) }
        }
    }

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
            btnSelectPhoto.setOnClickListener {
                showImagePickerDialog()
            }
            btnSavePemilih.setOnClickListener {
                if (validateData()) {
                    savePelimih();
                }
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
                foto = imagePath?: "",
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



    private fun showImagePickerDialog() {
        val options = arrayOf("Select from Gallery", "Capture Photo")
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Choose an option")
        builder.setItems(options) { _, which ->
            when (which) {
                0 -> selectImageFromGallery()
                1 -> requestCameraPermission.launch(Manifest.permission.CAMERA)
            }
        }
        builder.show()
    }

    private fun selectImageFromGallery() {
        selectImageLauncher.launch("image/*")
    }

    private fun captureImage() {
        val photoFile = createImageFile()
        val photoURI = FileProvider.getUriForFile(this, "${packageName}.provider", photoFile)
        imagePath = photoFile.absolutePath
        takePictureLauncher.launch(photoURI)
    }

    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File = cacheDir // Use cacheDir or getExternalFilesDir for temporary storage
        return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
    }

    private fun handleImageResult(uri: Uri) {
        val imageFile = createImageFile()
        contentResolver.openInputStream(uri)?.use { inputStream ->
            FileOutputStream(imageFile).use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        }
        imagePath = imageFile.absolutePath
        binding.imagePreview.setImageURI(uri)
    }

}