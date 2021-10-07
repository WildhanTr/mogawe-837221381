package com.mogawe.mosurvei.view.content.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.mogawe.mosurvei.R
import com.mogawe.mosurvei.databinding.ItemProfileGaweanPenugasanBinding

class AdapterGaweanPenugasan : RecyclerView.Adapter<AdapterGaweanPenugasan.ComponentItem>() {

    private var listPenugasan : MutableList<String> = mutableListOf("Penugasan_1", "Penugasan_2", "Penugasan_3", "Penugasan_4", "Penugasan_5", "Penugasan_6")
    var onItemClick: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ComponentItem(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_profile_gawean_penugasan, parent, false)
        )

    override fun onBindViewHolder(holder: ComponentItem, position: Int) {
        holder.bind(listPenugasan[position])
    }

    override fun getItemCount(): Int = listPenugasan.size

    fun setData(newList: List<String>?) {
        if (newList == null) return
        listPenugasan.clear()
        listPenugasan.addAll(newList)
        notifyDataSetChanged()
    }

    inner class ComponentItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemProfileGaweanPenugasanBinding.bind(itemView)
        fun bind(penugasan: String) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(R.drawable.noimagefound)
                    .transform(RoundedCorners(10))
                    .into(ivMenuProfile)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listPenugasan[adapterPosition])
            }
        }
    }
}