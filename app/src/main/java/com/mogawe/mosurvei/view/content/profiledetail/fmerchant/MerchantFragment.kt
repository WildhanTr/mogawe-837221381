package com.mogawe.mosurvei.view.content.profiledetail.fmerchant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.mogawe.mosurvei.R
import com.mogawe.mosurvei.databinding.FMerchantBinding
import com.mogawe.mosurvei.view.content.profile.AdapterGaweanEtalase

class MerchantFragment : Fragment() {

    private var fragmentBinding: FMerchantBinding? = null
    private val binding get() = fragmentBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentBinding = FMerchantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
    }

    private fun setupAdapter() {

        val adapterEtalase = AdapterGaweanEtalase()
        adapterEtalase.onItemClick = { select: String? ->
            Toast.makeText(requireContext(), "Etalase Select", Toast.LENGTH_SHORT).show()
        }

        val gridLayoutManager = GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
        binding.rvProflieMerchantEtalase.layoutManager = gridLayoutManager
        binding.rvProflieMerchantEtalase.adapter = adapterEtalase
    }

    companion object {
        @JvmStatic
        fun newInstance() = MerchantFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentBinding = null
    }
}