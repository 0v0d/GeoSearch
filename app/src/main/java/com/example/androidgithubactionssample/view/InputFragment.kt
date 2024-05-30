package com.example.androidgithubactionssample.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androidgithubactionssample.databinding.FragmentInputBinding

class InputFragment : Fragment() {
    private var _binding: FragmentInputBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInputBinding.inflate(inflater, container, false)
        binding.inputText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE && binding.inputText.text != null) {
                navigateToResultFragment(binding.inputText.text.toString())
                return@setOnEditorActionListener true
            }
            false
        }
        return binding.root
    }

    private fun navigateToResultFragment(text: String) {
        val action = InputFragmentDirections.actionToResultFragment(
            text
        )
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}