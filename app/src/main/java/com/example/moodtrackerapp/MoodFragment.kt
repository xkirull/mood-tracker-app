package com.example.moodtrackerapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.moodtrackerapp.databinding.FragmentMoodBinding
import kotlinx.coroutines.launch

class MoodFragment : Fragment() {
    private val viewModel: MoodViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMoodBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            if (viewModel.isTodayMoodSet()) {
                findNavController().navigate(R.id.statisticsFragment)
            }
        }

        binding.btnBadMood.setOnClickListener {
            saveAndNavigate("😔 Плохое настроение", "Не огорчайся, завтра будет лучше!")
        }
        binding.btnNeutralMood.setOnClickListener {
            saveAndNavigate("😐 Нормальное настроение", "Сегодня спокойный день!")
        }
        binding.btnGreatMood.setOnClickListener {
            saveAndNavigate("😀 Отличное настроение", "Потрясающее настроение, молодец!")
        }

        return binding.root
    }

    private fun saveAndNavigate(state: String, message: String) {
        viewModel.saveMood(state)
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_moodFragment_to_statisticsFragment)
    }
}
