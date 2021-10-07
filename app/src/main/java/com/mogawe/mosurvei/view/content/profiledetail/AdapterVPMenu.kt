package com.mogawe.mosurvei.view.content.profiledetail

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mogawe.mosurvei.view.content.profiledetail.fmerchant.MerchantFragment
import com.mogawe.mosurvei.view.content.profiledetail.fpersonal.PersonalFragment
import com.mogawe.mosurvei.view.content.profiledetail.fsettings.SettingsFragment

class AdapterVPMenu(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PersonalFragment.newInstance()
            1 -> PersonalFragment.newInstance()
            2 -> MerchantFragment.newInstance()
            3 -> SettingsFragment.newInstance()
            else -> throw IllegalStateException("Invalid adapter position")
        }
    }
    companion object {
        internal const val SCREEN_OFFSCREEN_LIMIT = 3
    }
}