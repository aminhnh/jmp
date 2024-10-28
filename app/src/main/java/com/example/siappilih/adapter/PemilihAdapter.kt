package com.example.siappilih.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.siappilih.database.Pemilih
import com.example.siappilih.databinding.ItemPemilihBinding


typealias OnClickPemilih = (Pemilih) -> Unit
class PemilihAdapter(private val listPemilih : List<Pemilih>, private val onClickPemilih : OnClickPemilih)
    : RecyclerView.Adapter<PemilihAdapter.ItemPemilihViewHolder>() {

    inner class ItemPemilihViewHolder(private val binding : ItemPemilihBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data : Pemilih){
            with(binding){
                txtNama.text = data.namaLengkap
                txtTanggal.text = data.tanggalPendataan
                itemView.setOnClickListener {
                    onClickPemilih(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemPemilihViewHolder {
        val binding = ItemPemilihBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemPemilihViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listPemilih.size
    }

    override fun onBindViewHolder(holder: ItemPemilihViewHolder, position: Int) {
        holder.bind(listPemilih[position])
    }
}