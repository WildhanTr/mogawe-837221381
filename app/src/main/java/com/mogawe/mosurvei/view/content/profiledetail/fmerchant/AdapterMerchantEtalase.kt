package com.mogawe.mosurvei.view.content.profiledetail.fmerchant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mogawe.mosurvei.R
import com.mogawe.mosurvei.databinding.ItemProfileGaweanEtalaseBinding

class AdapterMerchantEtalase : RecyclerView.Adapter<AdapterMerchantEtalase.ComponentItem>() {

    private var listEtalase : MutableList<String> = mutableListOf("Etalase_1", "Etalase_2", "Etalase_3", "Etalase_4", "Etalase_5", "Etalase_6")
    var onItemClick: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ComponentItem(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_profile_gawean_etalase, parent, false)
        )

    override fun onBindViewHolder(holder: ComponentItem, position: Int) {
        holder.bind(listEtalase[position])
    }

    override fun getItemCount(): Int = listEtalase.size

    fun setData(newList: List<String>?) {
        if (newList == null) return
        listEtalase.clear()
        listEtalase.addAll(newList)
        notifyDataSetChanged()
    }

    inner class ComponentItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemProfileGaweanEtalaseBinding.bind(itemView)
        fun bind(etalase: String) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(R.drawable.noimagefound)
                    .into(ivMenuProfile)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listEtalase[adapterPosition])
            }
        }
    }
}