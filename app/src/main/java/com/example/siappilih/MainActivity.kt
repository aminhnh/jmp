package com.example.siappilih

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.siappilih.database.PemilihDao
import com.example.siappilih.database.PemilihDatabase
import com.example.siappilih.databinding.ActivityMainBinding
import com.example.siappilih.menu.Menu
import com.example.siappilih.menu.MenuAdapter
import com.example.siappilih.util.AppExecutorService
import java.util.concurrent.ExecutorService

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var executorService: ExecutorService
    private lateinit var pemilihDao : PemilihDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        executorService = AppExecutorService.executorService

        val db = PemilihDatabase.getDatabase(this)
        pemilihDao = db!!.pemilihDao()!!

        title = "SiapPilih"
        val adapterMenu = MenuAdapter(generateMenu()) { menu ->
            val intent = when (menu.id) {
                1 -> Intent(this@MainActivity, InformasiActivity::class.java)  // Informasi
                2 -> Intent(this@MainActivity, FormEntryActivity::class.java)  // Form Entry
                3 -> Intent(this@MainActivity, LihatDataActivity::class.java)  // Lihat Data
                4 -> {
                    finish()
                    null
                }
                else -> null
            }

            intent?.let {
                it.putExtra("name", menu.name)
                it.putExtra("icon", menu.icon)
                startActivity(it)
            }
        }
        with(binding){
            rvMenu.apply {
                adapter = adapterMenu
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }
    }

    private fun generateMenu() : List<Menu> {
        return listOf(
            Menu(1, "Informasi", R.drawable.menu1),
            Menu(2, "Form Entry", R.drawable.menu1),
            Menu(3, "Lihat Data", R.drawable.menu1),
            Menu(4, "Keluar", R.drawable.menu1),
        )
    }
}