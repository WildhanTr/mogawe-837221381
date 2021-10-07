package com.mogawe.mosurvei.view.content.profiledetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import com.mogawe.mosurvei.R
import com.mogawe.mosurvei.databinding.AProfileDetailBinding

class ProfileDetailActivity : AppCompatActivity() {

    private lateinit var binding: AProfileDetailBinding

    companion object {
        private var selectedTabPosition = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AProfileDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapter()
        initOnClick()
    }

    private fun initOnClick() {
        binding.ivBack.setOnClickListener { finish() }
    }

    private fun setupAdapter() {
        val adapterVPMenu = AdapterVPMenu(this)
        binding.vpProfileMenu.apply {
            adapter = adapterVPMenu
            offscreenPageLimit = AdapterVPMenu.SCREEN_OFFSCREEN_LIMIT as Int
            currentItem = selectedTabPosition
        }

    }
}