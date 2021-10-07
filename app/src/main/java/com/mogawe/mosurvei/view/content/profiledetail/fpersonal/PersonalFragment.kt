package com.mogawe.mosurvei.view.content.profiledetail.fpersonal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mogawe.mosurvei.R

class PersonalFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.f_personal, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = PersonalFragment()
    }
}