package com.example.moodtrackerapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.moodtrackerapp.databinding.FragmentStatisticsBinding

class StatisticsFragment : Fragment() {
    private val viewModel: MoodViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentStatisticsBinding.inflate(inflater, container, false)

        viewModel.allMoods.observe(viewLifecycleOwner) { moods ->
            if (moods.isNullOrEmpty()) {
                binding.tvStatistics.text = "Нет данных для отображения статистики."
                Log.d("StatisticsFragment", "Данные не найдены.")
            } else {
                Log.d("StatisticsFragment", "Данные загружены: ${moods.size} записей.")
                binding.tvStatistics.text = moods.joinToString("\n") {
                    "${it.date} - ${it.state}"
                }
            }
        }

        return binding.root
    }
}
