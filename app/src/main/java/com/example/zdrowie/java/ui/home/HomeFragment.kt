package com.example.zdrowie.java.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.fragment.app.Fragment
import com.example.zdrowie.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        val textView = binding!!.textHome
        homeViewModel.getText()
            .observe(viewLifecycleOwner) { text: String? -> textView.text = text }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}