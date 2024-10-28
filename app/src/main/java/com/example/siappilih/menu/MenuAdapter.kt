package com.example.siappilih.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.siappilih.databinding.ItemMenuBinding

typealias OnClickMenu = (Menu) -> Unit
class MenuAdapter(private val listMenu : List<Menu>, private val onClickMenu : OnClickMenu)
    : RecyclerView.Adapter<MenuAdapter.ItemMenuViewHolder>() {

    inner class ItemMenuViewHolder(private val binding : ItemMenuBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data : Menu){
            with(binding){
                txtName.text = data.name
                imgIcon.setImageResource(data.icon)

                itemView.setOnClickListener {
                    onClickMenu(data)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMenuViewHolder {
        val binding = ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMenuViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listMenu.size
    }

    override fun onBindViewHolder(holder: ItemMenuViewHolder, position: Int) {
        holder.bind(listMenu[position])
    }
}