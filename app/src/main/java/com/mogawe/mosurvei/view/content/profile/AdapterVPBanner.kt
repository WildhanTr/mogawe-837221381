package com.mogawe.mosurvei.view.content.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mogawe.mosurvei.R
import com.mogawe.mosurvei.databinding.ItemProfileBannerBinding

class AdapterVPBanner : RecyclerView.Adapter<AdapterVPBanner.ComponentItem>() {

    private var listBanner : MutableList<String> = mutableListOf("BannerOne", "BannerTwo", "BannerThree", "BannerFour")
    var onItemClick: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ComponentItem(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_profile_banner, parent, false)
        )

    override fun onBindViewHolder(holder: ComponentItem, position: Int) {
        holder.bind(listBanner[position])
    }

    override fun getItemCount(): Int = listBanner.size

    fun setData(newList: List<String>?) {
        if (newList == null) return
        listBanner.clear()
        listBanner.addAll(newList)
        notifyDataSetChanged()
    }

    inner class ComponentItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemProfileBannerBinding.bind(itemView)
        fun bind(banner: String) {
            with(binding) {
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listBanner[adapterPosition])
            }
        }
    }
}