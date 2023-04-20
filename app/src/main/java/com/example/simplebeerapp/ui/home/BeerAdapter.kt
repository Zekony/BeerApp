package com.example.simplebeerapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.simplebeerapp.data.entities.Beer
import com.example.simplebeerapp.databinding.ItemBeerBinding
import com.example.simplebeerapp.ui.core.BeerAdapterClockListener

class BeerAdapter (val clickListener: BeerAdapterClockListener) :
    RecyclerView.Adapter<BeerAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemBeerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun configureView(item: Beer) {
            binding.tvTitle.text = item.name
            binding.tvPrice.text = "Цена от ${item.cost.toString()} ₽"
            binding.checkBox.isChecked = item.isFavorite

            binding.checkBox.setOnClickListener {
                val isFavorite = !item.isFavorite
                item.id.let {
                    clickListener.checkBoxUpdate(it, isFavorite)
                }
            }
            binding.root.setOnClickListener {
                item.id.let {
                    clickListener.navigateTo(it, item.name)
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBeerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int = differ.currentList.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.configureView(differ.currentList[position])
    }

    private val differCallBack = object :
        DiffUtil.ItemCallback<Beer>(){
        override fun areItemsTheSame(oldItem: Beer, newItem: Beer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Beer, newItem: Beer): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallBack)
}

