package com.mogawe.mosurvei.view.content.profiledetail.fsettings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mogawe.mosurvei.R

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.f_settings, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }
}